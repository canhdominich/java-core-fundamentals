import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Selfcheck {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<Integer> f = pool.submit(() -> 6 * 7);
        System.out.println(f.get());
        pool.shutdown();

        CountDownLatch latch = new CountDownLatch(3);
        latch.countDown();
        latch.countDown();
        System.out.println(latch.getCount());
        latch.countDown();
        latch.countDown();
        System.out.println(latch.getCount());
    }
}
