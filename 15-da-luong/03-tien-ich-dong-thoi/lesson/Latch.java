import java.util.concurrent.CountDownLatch;

public class Latch {
    public static void main(String[] args) throws InterruptedException {
        // Đếm ngược từ 3, mỗi barista xong thì giảm một
        CountDownLatch done = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                // Phần việc của barista
                done.countDown();
            }).start();
        }
        // Main đứng chờ tới khi bộ đếm về 0
        done.await();
        System.out.println("main resumed, remaining " + done.getCount());
    }
}
