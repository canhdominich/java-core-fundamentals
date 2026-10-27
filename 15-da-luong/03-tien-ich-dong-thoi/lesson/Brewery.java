import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.*;

public class Brewery {
    public static void main(String[] args) throws Exception {
        // Ba barista dùng đi dùng lại, không tạo luồng mới cho mỗi đơn
        ExecutorService baristas = Executors.newFixedThreadPool(3);
        List<Future<String>> tickets = new ArrayList<>();
        for (int order = 1; order <= 9; order++) {
            // Callable trả về tên luồng đã pha đơn này
            tickets.add(baristas.submit(() -> {
                Thread.sleep(50);
                return Thread.currentThread().getName();
            }));
        }
        Set<String> names = new TreeSet<>();
        for (Future<String> ticket : tickets) {
            // get() chờ tới khi đơn này pha xong
            names.add(ticket.get());
        }
        // Không gọi shutdown thì chương trình không bao giờ thoát
        baristas.shutdown();
        boolean finished = baristas.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("orders done " + tickets.size());
        System.out.println("threads used " + names);
        System.out.println("finished " + finished);
    }
}
