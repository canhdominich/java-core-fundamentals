import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TrapDemo {
    static class Order {
        final String customer;
        final int priority;

        Order(String customer, int priority) {
            this.customer = customer;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return customer + ":" + priority;
        }
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(Integer.MAX_VALUE, -5, 0, Integer.MIN_VALUE));
        nums.sort((x, y) -> x - y);  // phép trừ tràn số nên kết quả sai mà không báo lỗi
        System.out.println("subtract: " + nums);
        nums.sort((x, y) -> Integer.compare(x, y));
        System.out.println("compare:  " + nums);

        List<Order> orders = List.of(new Order("Chi", 2), new Order("An", 3), new Order("Binh", 2), new Order("Dung", 1));
        Comparator<Order> byPriority = Comparator.comparingInt(o -> o.priority);
        Comparator<Order> byName = Comparator.comparing(o -> o.customer);
        // Comparator trả 0 cho Chi và Binh nên TreeSet coi hai đơn là một
        Set<Order> loose = new TreeSet<>(byPriority);
        loose.addAll(orders);
        System.out.println("priority only: " + loose + " size " + loose.size());
        // Thêm tên làm tiêu chí phụ thì hai khách khác nhau không bao giờ hoà
        Set<Order> strict = new TreeSet<>(byPriority.thenComparing(byName));
        strict.addAll(orders);
        System.out.println("with name:     " + strict + " size " + strict.size());
    }
}
