package dev.canhdominich.core.threads.sync;

import java.lang.management.ManagementFactory;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public final class Deadlocks {

    private Deadlocks() {
    }

    public static String opposingOrder() throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        CountDownLatch bothHoldFirst = new CountDownLatch(2);
        Thread t1 = new Thread(() -> {
            synchronized (a) {
                bothHoldFirst.countDown();
                await(bothHoldFirst);
                synchronized (b) {
                }
            }
        }, "locks-a-then-b");
        Thread t2 = new Thread(() -> {
            synchronized (b) {
                bothHoldFirst.countDown();
                await(bothHoldFirst);
                synchronized (a) {
                }
            }
        }, "locks-b-then-a");
        t1.setDaemon(true);
        t2.setDaemon(true);
        t1.start();
        t2.start();
        long[] ids = null;
        long deadline = System.nanoTime() + 5_000_000_000L;
        while (ids == null && System.nanoTime() < deadline) {
            Thread.sleep(20);
            ids = ManagementFactory.getThreadMXBean().findDeadlockedThreads();
        }
        int found = ids == null ? 0 : ids.length;
        return "threads reported deadlocked " + found + ", states " + t1.getState() + " and " + t2.getState();
    }

    public static String sameOrder() throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Runnable task = () -> {
            synchronized (a) {
                synchronized (b) {
                }
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join(2000);
        t2.join(2000);
        return "both threads finished " + (!t1.isAlive() && !t2.isAlive());
    }

    public static String tryLockBackoff() throws InterruptedException {
        ReentrantLock a = new ReentrantLock();
        ReentrantLock b = new ReentrantLock();
        CountDownLatch bothHoldFirst = new CountDownLatch(2);
        boolean[] gaveUp = new boolean[2];
        Thread t1 = new Thread(() -> gaveUp[0] = !takeBoth(a, b, bothHoldFirst));
        Thread t2 = new Thread(() -> gaveUp[1] = !takeBoth(b, a, bothHoldFirst));
        t1.start();
        t2.start();
        t1.join(5000);
        t2.join(5000);
        return "both finished " + (!t1.isAlive() && !t2.isAlive()) + ", at least one gave up and could retry " + (gaveUp[0] || gaveUp[1]);
    }

    private static boolean takeBoth(ReentrantLock first, ReentrantLock second, CountDownLatch gate) {
        first.lock();
        try {
            gate.countDown();
            await(gate);
            try {
                if (second.tryLock(200, TimeUnit.MILLISECONDS)) {
                    second.unlock();
                    return true;
                }
                return false;
            } catch (InterruptedException e) {
                return false;
            }
        } finally {
            first.unlock();
        }
    }

    private static void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
