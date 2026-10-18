package dev.canhdominich.core.memory;

public final class ThreadStacks {

    private ThreadStacks() {
    }

    public static int[] run(int perThread) throws InterruptedException {
        int[] shared = new int[1];
        int[] locals = new int[2];
        Runnable[] work = new Runnable[2];
        for (int t = 0; t < 2; t++) {
            int index = t;
            work[t] = () -> {
                int local = 0;
                for (int i = 0; i < perThread; i++) {
                    local++;
                    shared[0]++;
                }
                locals[index] = local;
            };
        }
        Thread first = new Thread(work[0]);
        Thread second = new Thread(work[1]);
        first.start();
        second.start();
        first.join();
        second.join();
        return new int[] {locals[0], locals[1], shared[0]};
    }
}
