import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Challenge {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        AtomicInteger cups = new AtomicInteger();
        CountDownLatch done = new CountDownLatch(5);
        for (int b = 0; b < 5; b++) {
            pool.submit(() -> {
                try {
                    for (int i = 0; i < 200; i++) {
                        cups.incrementAndGet();
                    }
                } finally {
                    // Luôn đếm ngược dù việc có lỗi
                    done.countDown();
                }
            });
        }
        // Chờ tối đa 2 giây, trả về false nếu hết giờ mà chưa đủ
        boolean allDone = done.await(2, TimeUnit.SECONDS);
        pool.shutdown();
        System.out.println("cups " + cups.get());
        System.out.println("latch done " + allDone);
    }
}
