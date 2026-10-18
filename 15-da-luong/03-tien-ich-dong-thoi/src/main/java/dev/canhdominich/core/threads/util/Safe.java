package dev.canhdominich.core.threads.util;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public final class Safe {

    private Safe() {
    }

    static int hammer(Runnable action, int threads, int perThread) throws InterruptedException {
        List<Thread> all = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            Thread t = new Thread(() -> {
                for (int k = 0; k < perThread; k++) {
                    action.run();
                }
            });
            all.add(t);
            t.start();
        }
        for (Thread t : all) {
            t.join();
        }
        return threads * perThread;
    }

    public static String atomicCounters() throws InterruptedException {
        AtomicInteger atomic = new AtomicInteger();
        LongAdder adder = new LongAdder();
        int expected = hammer(() -> {
            atomic.incrementAndGet();
            adder.increment();
        }, 4, 200_000);
        return "expected " + expected + ", AtomicInteger " + atomic.get() + ", LongAdder " + adder.sum();
    }

    public static String compareAndSet() {
        AtomicInteger value = new AtomicInteger(10);
        boolean first = value.compareAndSet(10, 11);
        boolean second = value.compareAndSet(10, 12);
        int before = value.get();
        int updated = value.updateAndGet(v -> v * 2);
        return "compareAndSet(10, 11) " + first + ", then compareAndSet(10, 12) " + second + ", value " + before + " before doubling, " + updated + " after updateAndGet";
    }

    public static String concurrentMapCountsExactly() throws InterruptedException {
        Map<String, Integer> counts = new ConcurrentHashMap<>();
        int total = hammer(() -> counts.merge("hit", 1, Integer::sum), 4, 50_000);
        return "expected " + total + ", ConcurrentHashMap.merge counted " + counts.get("hit");
    }

    public static String iterationWhileModifying() {
        List<Integer> plain = new ArrayList<>(List.of(1, 2, 3));
        String plainResult;
        try {
            for (Integer x : plain) {
                if (x == 1) {
                    plain.add(4);
                }
            }
            plainResult = "no error";
        } catch (ConcurrentModificationException e) {
            plainResult = e.getClass().getSimpleName();
        }
        List<Integer> cow = new CopyOnWriteArrayList<>(List.of(1, 2, 3));
        List<Integer> seen = new ArrayList<>();
        for (Integer x : cow) {
            seen.add(x);
            if (x == 1) {
                cow.add(4);
            }
        }
        return "ArrayList: " + plainResult + ", CopyOnWriteArrayList: iterated " + seen + " and now holds " + cow;
    }

    public static String blockingQueue() throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        List<Integer> got = new ArrayList<>();
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    int v = queue.take();
                    if (v < 0) {
                        return;
                    }
                    got.add(v);
                }
            } catch (InterruptedException ignored) {
            }
        });
        consumer.start();
        for (int i = 1; i <= 6; i++) {
            queue.put(i);
        }
        queue.put(-1);
        consumer.join();
        return "consumer got " + got + " through a queue of capacity 2";
    }
}
