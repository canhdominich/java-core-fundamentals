package dev.canhdominich.core.threads.lifecycle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public final class States {

    private States() {
    }

    static void awaitState(Thread t, Thread.State wanted) throws InterruptedException {
        long deadline = System.nanoTime() + 5_000_000_000L;
        while (t.getState() != wanted) {
            if (System.nanoTime() > deadline) {
                throw new IllegalStateException("never reached " + wanted + ", stuck in " + t.getState());
            }
            Thread.sleep(1);
        }
    }

    public static List<Thread.State> walk() throws InterruptedException {
        List<Thread.State> seen = new ArrayList<>();
        Object monitor = new Object();
        AtomicBoolean spin = new AtomicBoolean(true);
        Thread other = new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ignored) {
            }
        }, "other");
        Thread t = new Thread(() -> {
            while (spin.get()) {
                Thread.onSpinWait();
            }
            try {
                Thread.sleep(60_000);
            } catch (InterruptedException ignored) {
            }
            try {
                other.join();
            } catch (InterruptedException ignored) {
            }
            synchronized (monitor) {
            }
        }, "walker");
        seen.add(t.getState());
        t.start();
        awaitState(t, Thread.State.RUNNABLE);
        seen.add(t.getState());
        spin.set(false);
        awaitState(t, Thread.State.TIMED_WAITING);
        seen.add(t.getState());
        other.start();
        synchronized (monitor) {
            t.interrupt();
            awaitState(t, Thread.State.WAITING);
            seen.add(t.getState());
            awaitState(t, Thread.State.BLOCKED);
            seen.add(t.getState());
        }
        t.join();
        seen.add(t.getState());
        return seen;
    }

    public static String waitingKinds() throws InterruptedException {
        Object lock = new Object();
        Thread plainWait = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException ignored) {
                }
            }
        });
        Thread timedWait = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait(60_000);
                } catch (InterruptedException ignored) {
                }
            }
        });
        plainWait.setDaemon(true);
        timedWait.setDaemon(true);
        plainWait.start();
        timedWait.start();
        awaitState(plainWait, Thread.State.WAITING);
        awaitState(timedWait, Thread.State.TIMED_WAITING);
        String result = "wait() " + plainWait.getState() + ", wait(ms) " + timedWait.getState();
        plainWait.interrupt();
        timedWait.interrupt();
        plainWait.join();
        timedWait.join();
        return result;
    }
}
