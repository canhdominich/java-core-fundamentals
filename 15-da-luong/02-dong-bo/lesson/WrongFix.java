public class WrongFix {
    private int total;
    // Khoá là một trường final dùng chung, không tạo mới mỗi lần
    private final Object lock = new Object();

    void add(int cups) {
        synchronized (lock) {
            total += cups;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongFix shop = new WrongFix();
        Runnable work = () -> {
            for (int i = 0; i < 100_000; i++) {
                shop.add(1);
            }
        };
        Thread a = new Thread(work);
        Thread b = new Thread(work);
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println(shop.total);
    }
}
