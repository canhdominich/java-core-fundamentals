package dev.canhdominich.core.threads.sync;

public final class Visibility {

    private static volatile boolean volatileStop;
    private static boolean plainStop;

    private Visibility() {
    }

    public static boolean volatileFlagIsSeen() throws InterruptedException {
        volatileStop = false;
        long[] spins = new long[1];
        Thread reader = new Thread(() -> {
            while (!volatileStop) {
                spins[0]++;
            }
        });
        reader.setDaemon(true);
        reader.start();
        Thread.sleep(100);
        volatileStop = true;
        reader.join(2000);
        return !reader.isAlive();
    }

    public static boolean plainFlagIsSeen() throws InterruptedException {
        plainStop = false;
        long[] spins = new long[1];
        Thread reader = new Thread(() -> {
            while (!plainStop) {
                spins[0]++;
            }
        });
        reader.setDaemon(true);
        reader.start();
        Thread.sleep(100);
        plainStop = true;
        reader.join(2000);
        return !reader.isAlive();
    }
}
