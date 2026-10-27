import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NaturalOrder {
    static class Order implements Comparable<Order> {
        final String customer;
        final int priority;

        Order(String customer, int priority) {
            this.customer = customer;
            this.priority = priority;
        }

        // Âm: this đứng trước, 0: hoà, dương: this đứng sau
        @Override
        public int compareTo(Order other) {
            return Integer.compare(priority, other.priority);
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
        Collections.sort(orders);  // sort ổn định: Chi vẫn đứng trước Binh vì cùng ưu tiên
        System.out.println(orders);
    }
}
