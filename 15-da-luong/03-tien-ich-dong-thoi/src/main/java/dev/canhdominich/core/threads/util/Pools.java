package dev.canhdominich.core.threads.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class Pools {

    private Pools() {
    }

    static ThreadFactory named(String prefix) {
        AtomicInteger n = new AtomicInteger();
        return r -> new Thread(r, prefix + "-" + n.incrementAndGet());
    }

    public static String fixedPoolQueuesTheExtraTasks() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3, named("worker"));
        AtomicInteger running = new AtomicInteger();
        AtomicInteger maxRunning = new AtomicInteger();
        Set<String> names = ConcurrentHashMap.newKeySet();
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            futures.add(pool.submit(() -> {
                int now = running.incrementAndGet();
                maxRunning.accumulateAndGet(now, Math::max);
                names.add(Thread.currentThread().getName());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {
                }
                running.decrementAndGet();
            }));
        }
        pool.shutdown();
        boolean finished = pool.awaitTermination(5, TimeUnit.SECONDS);
        return "9 tasks on a pool of 3: finished " + finished + ", most running at once " + maxRunning.get() + ", distinct threads used " + names.size();
    }

    public static String submitAfterShutdown() {
        ExecutorService pool = Executors.newSingleThreadExecutor(named("late"));
        pool.shutdown();
        try {
            pool.submit(() -> 1);
            return "accepted";
        } catch (RejectedExecutionException e) {
            return e.getClass().getSimpleName() + ", isShutdown " + pool.isShutdown();
        }
    }

    public static String shutdownNowReturnsThePending() throws InterruptedException {
        ExecutorService pool = Executors.newSingleThreadExecutor(named("single"));
        CountDownLatch started = new CountDownLatch(1);
        boolean[] interrupted = new boolean[1];
        pool.submit(() -> {
            started.countDown();
            try {
                Thread.sleep(60_000);
            } catch (InterruptedException e) {
                interrupted[0] = true;
            }
        });
        for (int i = 0; i < 4; i++) {
            pool.submit(() -> {
            });
        }
        started.await();
        List<Runnable> pending = pool.shutdownNow();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        return "shutdownNow returned " + pending.size() + " waiting tasks, the running task was interrupted " + interrupted[0] + ", terminated " + pool.isTerminated();
    }

    public static String poolThreadsKeepTheirNames() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(2, named("billing"));
        Future<String> f = pool.submit(() -> Thread.currentThread().getName());
        String name = f.get();
        pool.shutdown();
        return "task ran on " + name;
    }
}
