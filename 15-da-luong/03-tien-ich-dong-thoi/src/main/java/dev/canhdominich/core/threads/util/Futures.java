package dev.canhdominich.core.threads.util;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Futures {

    private Futures() {
    }

    public static String callableReturnsAValue() throws Exception {
        ExecutorService pool = Executors.newSingleThreadExecutor(Pools.named("calc"));
        Callable<Integer> sum = () -> 2 + 3;
        Future<Integer> f = pool.submit(sum);
        int value = f.get();
        pool.shutdown();
        return "get returned " + value + ", isDone " + f.isDone();
    }

    public static String exceptionIsWrapped() throws Exception {
        ExecutorService pool = Executors.newSingleThreadExecutor(Pools.named("fail"));
        Future<Integer> f = pool.submit(() -> {
            throw new IllegalStateException("price service down");
        });
        try {
            f.get();
            return "no error";
        } catch (ExecutionException e) {
            return "get threw " + e.getClass().getSimpleName() + " whose cause is " + e.getCause().getClass().getSimpleName() + ": " + e.getCause().getMessage();
        } finally {
            pool.shutdown();
        }
    }

    public static String timeoutAndCancel() throws Exception {
        ExecutorService pool = Executors.newSingleThreadExecutor(Pools.named("slow"));
        CountDownLatch started = new CountDownLatch(1);
        boolean[] interrupted = new boolean[1];
        Future<?> f = pool.submit(() -> {
            started.countDown();
            try {
                Thread.sleep(60_000);
            } catch (InterruptedException e) {
                interrupted[0] = true;
            }
        });
        started.await();
        String timeout;
        try {
            f.get(100, TimeUnit.MILLISECONDS);
            timeout = "finished";
        } catch (TimeoutException e) {
            timeout = "TimeoutException after 100 ms";
        }
        boolean cancelled = f.cancel(true);
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        return timeout + ", cancel(true) " + cancelled + ", isCancelled " + f.isCancelled() + ", task interrupted " + interrupted[0];
    }

    public static String completableChain() {
        CompletableFuture<Integer> price = CompletableFuture.supplyAsync(() -> 120);
        CompletableFuture<Integer> quantity = CompletableFuture.supplyAsync(() -> 3);
        CompletableFuture<String> total = price.thenCombine(quantity, (p, q) -> p * q).thenApply(t -> "total " + t);
        return total.join();
    }

    public static String completableFailure() {
        CompletableFuture<Integer> broken = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new IllegalArgumentException("bad input");
            }
            return 1;
        });
        String recovered = broken.exceptionally(e -> -1).thenApply(v -> "recovered with " + v).join();
        String raw;
        try {
            broken.join();
            raw = "no error";
        } catch (java.util.concurrent.CompletionException e) {
            raw = "join threw CompletionException wrapping " + e.getCause().getClass().getSimpleName();
        }
        return recovered + ", " + raw;
    }

    public static String completableOnCustomPool() {
        ExecutorService pool = Executors.newFixedThreadPool(2, Pools.named("orders"));
        String onCommon = CompletableFuture.supplyAsync(() -> Thread.currentThread().getName()).join();
        String onCustom = CompletableFuture.supplyAsync(() -> Thread.currentThread().getName(), pool).join();
        pool.shutdown();
        return "default pool thread starts with " + onCommon.substring(0, onCommon.lastIndexOf('-')) + ", custom pool thread " + onCustom;
    }

    public static String allOf() {
        CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<Integer> c = CompletableFuture.supplyAsync(() -> 3);
        CompletableFuture.allOf(a, b, c).join();
        return "allOf waited for all three, sum " + (a.join() + b.join() + c.join());
    }
}
