import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderSort {
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
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("Chi", 2));
        orders.add(new Order("An", 3));
        orders.add(new Order("Binh", 2));
        orders.add(new Order("Dung", 1));

        // Hai tiêu chí tách riêng, ghép lại bằng thenComparing
        Comparator<Order> byPriority = Comparator.comparingInt(o -> o.priority);
        Comparator<Order> byName = Comparator.comparing(o -> o.customer);
        orders.sort(byPriority.thenComparing(byName));  // byName chỉ quyết định khi ưu tiên bằng nhau
        System.out.println(orders);
    }
}
