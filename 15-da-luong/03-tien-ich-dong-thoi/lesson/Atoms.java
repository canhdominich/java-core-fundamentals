import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Atoms {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger total = new AtomicInteger();
        ConcurrentHashMap<String, Integer> menu = new ConcurrentHashMap<>();
        Runnable shift = () -> {
            for (int i = 0; i < 100_000; i++) {
                // Cộng nguyên tử, không cần khoá
                total.incrementAndGet();
                // merge cộng dồn theo khoá trong một bước nguyên tử
                menu.merge("latte", 1, Integer::sum);
            }
        };
        Thread[] threads = new Thread[4];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(shift);
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("total " + total.get() + ", latte " + menu.get("latte"));

        AtomicInteger stock = new AtomicInteger(10);
        // Chỉ đổi nếu giá trị vẫn là giá trị mình đã đọc
        boolean first = stock.compareAndSet(10, 11);
        boolean second = stock.compareAndSet(10, 12);
        System.out.println(first + " " + second + " " + stock.get());
    }
}
