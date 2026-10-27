import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PriceTotal {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        List<Future<Integer>> results = new ArrayList<>();
        for (int price : new int[] {120, 80, 45, 200, 15}) {
            Callable<Integer> lookup = () -> price;
            results.add(pool.submit(lookup));
        }
        results.add(pool.submit(() -> {
            throw new IllegalStateException("price service down");
        }));
        int total = 0;
        for (Future<Integer> result : results) {
            try {
                total += result.get();
            } catch (ExecutionException e) {
                // Việc lỗi không làm hỏng tổng của các việc khác
                System.out.println("failed: " + e.getCause().getMessage());
            }
        }
        pool.shutdown();
        System.out.println("total " + total);
    }
}
