public class Visibility {
    // volatile: ghi của luồng này luôn được luồng khác thấy
    static volatile boolean volatileStop;
    // Biến thường: luồng đọc có thể không bao giờ thấy giá trị mới
    static boolean plainStop;
    static long spins;

    public static void main(String[] args) throws InterruptedException {
        Thread v = new Thread(() -> {
            while (!volatileStop) {
                spins++;
            }
        });
        v.setDaemon(true);
        v.start();
        Thread.sleep(100);
        volatileStop = true;
        v.join(2000);
        System.out.println("volatile flag: reader stopped " + !v.isAlive());

        Thread p = new Thread(() -> {
            while (!plainStop) {
                spins++;
            }
        });
        p.setDaemon(true);
        p.start();
        Thread.sleep(100);
        plainStop = true;
        // Chờ tối đa 2 giây rồi bỏ cuộc, kết quả plain không được bảo đảm
        p.join(2000);
        System.out.println("plain flag: reader stopped " + !p.isAlive());
    }
}
