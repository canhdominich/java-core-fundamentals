package dev.canhdominich.core.threads.lifecycle;

import java.util.concurrent.atomic.AtomicInteger;

public final class Stopping {

    private Stopping() {
    }

    public static String joinSeesTheResult() throws InterruptedException {
        int[] box = new int[1];
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(150);
            } catch (InterruptedException ignored) {
            }
            box[0] = 42;
        });
        t.start();
        int before = box[0];
        boolean aliveBefore = t.isAlive();
        t.join();
        return "before join result " + before + " alive " + aliveBefore + ", after join result " + box[0] + " alive " + t.isAlive();
    }

    public static String interruptASleeper() throws InterruptedException {
        String[] message = new String[1];
        boolean[] flagInCatch = new boolean[1];
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(60_000);
            } catch (InterruptedException e) {
                message[0] = e.getMessage();
                flagInCatch[0] = Thread.currentThread().isInterrupted();
            }
        });
        t.start();
        States.awaitState(t, Thread.State.TIMED_WAITING);
        t.interrupt();
        t.join();
        return "caught InterruptedException with message '" + message[0] + "', interrupt flag inside catch " + flagInCatch[0] + ", state " + t.getState();
    }

    public static String cooperativeStop() throws InterruptedException {
        AtomicInteger loops = new AtomicInteger();
        boolean[] sawFlag = new boolean[1];
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                loops.incrementAndGet();
                Thread.onSpinWait();
            }
            sawFlag[0] = true;
        });
        t.start();
        Thread.sleep(50);
        t.interrupt();
        t.join(2000);
        return "loop saw the flag " + sawFlag[0] + ", still alive " + t.isAlive() + ", iterations ran " + (loops.get() > 0);
    }

    public static String ignoredInterrupt() throws InterruptedException {
        AtomicInteger loops = new AtomicInteger();
        Thread t = new Thread(() -> {
            long end = System.nanoTime() + 300_000_000L;
            while (System.nanoTime() < end) {
                loops.incrementAndGet();
            }
        });
        t.start();
        t.interrupt();
        t.join(2000);
        return "interrupt on a busy loop that never checks the flag: thread finished normally " + (!t.isAlive()) + ", iterations ran " + (loops.get() > 0);
    }

    public static String uncaught() throws InterruptedException {
        String[] captured = new String[1];
        Thread t = new Thread(() -> {
            throw new IllegalStateException("boom");
        }, "crasher");
        t.setUncaughtExceptionHandler((thread, error) -> captured[0] = thread.getName() + " died with " + error.getClass().getSimpleName() + ": " + error.getMessage());
        t.start();
        t.join();
        return captured[0] + ", state " + t.getState() + ", main still running " + Thread.currentThread().isAlive();
    }
}
