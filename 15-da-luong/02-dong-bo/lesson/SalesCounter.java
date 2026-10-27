public class SalesCounter {
    static int unsafe;
    static int safe;
    static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable shift = () -> {
            for (int i = 0; i < 100_000; i++) {
                // Đọc, cộng, ghi là ba bước nên có thể bị xen kẽ
                unsafe++;
                // Mỗi lần chỉ một luồng vào khối này
                synchronized (LOCK) {
                    safe++;
                }
            }
        };
        Thread a = new Thread(shift);
        Thread b = new Thread(shift);
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println("expected 200000");
        System.out.println("safe " + safe);
        // Số này khác nhau giữa các lần chạy
        System.out.println("unsafe " + unsafe);
        System.out.println("unsafe at most expected " + (unsafe <= 200_000));
    }
}
