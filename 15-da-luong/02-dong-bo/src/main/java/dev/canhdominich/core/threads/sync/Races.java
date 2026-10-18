package dev.canhdominich.core.threads.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

public final class Races {

    private Races() {
    }

    public interface Counter {
        void increment();

        int get();
    }

    public static final class Unsafe implements Counter {
        private int count;

        public void increment() {
            count++;
        }

        public int get() {
            return count;
        }
    }

    public static final class SyncMethod implements Counter {
        private int count;

        public synchronized void increment() {
            count++;
        }

        public synchronized int get() {
            return count;
        }
    }

    public static final class SyncBlock implements Counter {
        private final Object lock = new Object();
        private int count;

        public void increment() {
            synchronized (lock) {
                count++;
            }
        }

        public int get() {
            synchronized (lock) {
                return count;
            }
        }
    }

    public static final class VolatileCounter implements Counter {
        private volatile int count;

        public void increment() {
            count++;
        }

        public int get() {
            return count;
        }
    }

    public static final class WrongLock implements Counter {
        private int count;

        public void increment() {
            synchronized (new Object()) {
                count++;
            }
        }

        public int get() {
            return count;
        }
    }

    public static final class Reentrant {
        public synchronized String outer() {
            return "outer then " + inner();
        }

        public synchronized String inner() {
            return "inner on the same lock";
        }
    }

    public static int run(Supplier<Counter> factory, int threads, int perThread) throws InterruptedException {
        Counter counter = factory.get();
        CountDownLatch go = new CountDownLatch(1);
        List<Thread> all = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            Thread t = new Thread(() -> {
                try {
                    go.await();
                } catch (InterruptedException e) {
                    return;
                }
                for (int k = 0; k < perThread; k++) {
                    counter.increment();
                }
            });
            all.add(t);
            t.start();
        }
        go.countDown();
        for (Thread t : all) {
            t.join();
        }
        return counter.get();
    }

    public static String compare() throws InterruptedException {
        int threads = 4;
        int per = 200_000;
        int expected = threads * per;
        int lostRuns = 0;
        int volatileLost = 0;
        int wrongLockLost = 0;
        for (int i = 0; i < 5; i++) {
            if (run(Unsafe::new, threads, per) < expected) {
                lostRuns++;
            }
            if (run(VolatileCounter::new, threads, per) < expected) {
                volatileLost++;
            }
            if (run(WrongLock::new, threads, per) < expected) {
                wrongLockLost++;
            }
        }
        return "expected " + expected + ", synchronized method " + run(SyncMethod::new, threads, per) + ", synchronized block " + run(SyncBlock::new, threads, per) + ", plain int lost updates in " + lostRuns + " of 5 runs, volatile int in " + volatileLost + " of 5, synchronized on a new object each time in " + wrongLockLost + " of 5";
    }

    public static String reentrancy() {
        return new Reentrant().outer();
    }
}
