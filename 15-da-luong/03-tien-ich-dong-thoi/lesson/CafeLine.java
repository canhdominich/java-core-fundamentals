import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CafeLine {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<String> latte = pool.submit(() -> "latte");
        System.out.println("step 1: " + latte.get());

        Future<String> broken = pool.submit(() -> {
            throw new IllegalStateException("price service down");
        });
        try {
            broken.get();
        } catch (ExecutionException e) {
            // Lỗi thật của việc nằm trong getCause()
            System.out.println("step 2: " + e.getCause().getMessage());
        }

        AtomicInteger served = new AtomicInteger();
        CountDownLatch done = new CountDownLatch(4);
        for (int i = 0; i < 4; i++) {
            pool.submit(() -> {
                for (int k = 0; k < 1000; k++) {
                    served.incrementAndGet();
                }
                done.countDown();
            });
        }
        // Chờ đủ 4 việc báo xong
        done.await();
        System.out.println("step 3: " + served.get());

        pool.shutdown();
        System.out.println("step 4: " + pool.awaitTermination(5, TimeUnit.SECONDS));
    }
}
