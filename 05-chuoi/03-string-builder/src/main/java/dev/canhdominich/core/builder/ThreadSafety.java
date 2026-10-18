package dev.canhdominich.core.builder;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafety {

    public static int[] race(Appendable target, int perThread) throws InterruptedException {
        AtomicInteger failures = new AtomicInteger();
        Runnable work = () -> {
            for (int i = 0; i < perThread; i++) {
                try {
                    target.append('x');
                } catch (Throwable error) {
                    failures.incrementAndGet();
                    return;
                }
            }
        };
        Thread first = new Thread(work);
        Thread second = new Thread(work);
        first.start();
        second.start();
        first.join();
        second.join();
        return new int[] {((CharSequence) target).length(), failures.get()};
    }

    public static void main(String[] args) throws InterruptedException {
        int perThread = 200_000;
        int expected = 2 * perThread;
        for (int attempt = 1; attempt <= 3; attempt++) {
            int[] builder = race(new StringBuilder(), perThread);
            int[] buffer = race(new StringBuffer(), perThread);
            System.out.println("attempt " + attempt + ": StringBuilder length " + builder[0] + " of " + expected + ", failed threads " + builder[1]
                    + " | StringBuffer length " + buffer[0] + " of " + expected + ", failed threads " + buffer[1]);
        }
    }
}
