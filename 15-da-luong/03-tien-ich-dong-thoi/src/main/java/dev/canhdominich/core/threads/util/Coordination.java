package dev.canhdominich.core.threads.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public final class Coordination {

    private Coordination() {
    }

    public static String latchWaitsForWorkers() throws InterruptedException {
        CountDownLatch done = new CountDownLatch(3);
        AtomicInteger finished = new AtomicInteger();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {
                }
                finished.incrementAndGet();
                done.countDown();
            }).start();
        }
        done.await();
        return "main resumed after " + finished.get() + " workers called countDown, remaining count " + done.getCount();
    }

    public static String semaphoreLimitsConcurrency() throws InterruptedException {
        Semaphore permits = new Semaphore(2);
        AtomicInteger inside = new AtomicInteger();
        AtomicInteger maxInside = new AtomicInteger();
        List<Thread> all = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Thread t = new Thread(() -> {
                try {
                    permits.acquire();
                    try {
                        maxInside.accumulateAndGet(inside.incrementAndGet(), Math::max);
                        Thread.sleep(40);
                        inside.decrementAndGet();
                    } finally {
                        permits.release();
                    }
                } catch (InterruptedException ignored) {
                }
            });
            all.add(t);
            t.start();
        }
        for (Thread t : all) {
            t.join();
        }
        return "6 threads, 2 permits: at most " + maxInside.get() + " inside at once, permits back to " + permits.availablePermits();
    }

    public static String barrierSynchronizesRounds() throws InterruptedException {
        List<String> log = Collections.synchronizedList(new ArrayList<>());
        AtomicInteger round = new AtomicInteger();
        CyclicBarrier barrier = new CyclicBarrier(3, () -> log.add("round " + round.incrementAndGet() + " complete"));
        List<Thread> all = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(() -> {
                try {
                    barrier.await();
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException ignored) {
                }
            });
            all.add(t);
            t.start();
        }
        for (Thread t : all) {
            t.join();
        }
        return log.toString();
    }
}
