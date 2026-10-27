import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Timeouts {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        CountDownLatch interrupted = new CountDownLatch(1);
        Future<String> slow = pool.submit(() -> {
            try {
                Thread.sleep(60_000);
            } catch (InterruptedException e) {
                interrupted.countDown();
            }
            return "late";
        });
        try {
            slow.get(100, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            System.out.println("TimeoutException after 100 ms");
        }
        // cancel(true) ngắt luồng đang chạy việc đó
        System.out.println("cancel " + slow.cancel(true) + ", cancelled " + slow.isCancelled());
        System.out.println("task saw the interrupt " + interrupted.await(2, TimeUnit.SECONDS));

        Future<Integer> failing = pool.submit(() -> {
            throw new IllegalStateException("price service down");
        });
        try {
            failing.get();
        } catch (ExecutionException e) {
            // Lỗi thật nằm trong getCause()
            System.out.println("get threw ExecutionException, cause " + e.getCause());
        }
        pool.shutdown();
    }
}
