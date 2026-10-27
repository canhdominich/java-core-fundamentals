public class Selfcheck {
    static int n;
    static final Object L = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            for (int i = 0; i < 1000; i++) {
                // Cùng một khoá L cho cả hai luồng
                synchronized (L) {
                    n++;
                }
            }
        };
        Thread a = new Thread(r);
        Thread b = new Thread(r);
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println(n);
    }
}
