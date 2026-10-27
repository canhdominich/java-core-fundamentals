import java.util.concurrent.atomic.AtomicInteger;

public class Exercise3 {
    private final AtomicInteger sold = new AtomicInteger();

    void sell() {
        // Một lệnh nguyên tử thay cho khối synchronized
        sold.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        Exercise3 shop = new Exercise3();
        Runnable work = () -> {
            for (int i = 0; i < 100_000; i++) {
                shop.sell();
            }
        };
        Thread[] threads = new Thread[4];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(work);
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println(shop.sold.get());
    }
}
