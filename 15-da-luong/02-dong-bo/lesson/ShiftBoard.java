public class ShiftBoard {
    static int sold;
    static int soldLocked;
    static int soldMethod;
    static final Object LOCK = new Object();
    static volatile boolean closed;

    // Khoá là lớp ShiftBoard
    static synchronized void addMethod() {
        soldMethod++;
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable shift = () -> {
            for (int i = 0; i < 50_000; i++) {
                // Không bảo vệ nên có thể mất lần cộng
                sold++;
                synchronized (LOCK) {
                    soldLocked++;
                }
                addMethod();
            }
        };
        Thread a = new Thread(shift);
        Thread b = new Thread(shift);
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println("step 1: at most 100000 " + (sold <= 100_000));
        System.out.println("step 2: " + soldLocked);
        System.out.println("step 3: " + soldMethod);

        Thread watcher = new Thread(() -> {
            while (!closed) {
                Thread.onSpinWait();
            }
        });
        // Luồng watcher chạy song song với main
        watcher.start();
        closed = true;
        watcher.join();
        System.out.println("step 4: watcher stopped " + !watcher.isAlive());
    }
}
