import java.util.List;
import java.util.Optional;

public class FindOrder {
    static class Order {
        final String customer;
        final String drink;
        final int priority;

        Order(String customer, String drink, int priority) {
            this.customer = customer;
            this.drink = drink;
            this.priority = priority;
        }
    }

    // Có thể không có đơn khẩn nào, nên trả về Optional thay cho null
    static Optional<Order> findUrgent(List<Order> orders, String customer) {
        return orders.stream()
                .filter(o -> o.customer.equals(customer) && o.priority == 1)
                .findFirst();
    }

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("An", "Latte", 2), new Order("Binh", "Mocha", 1), new Order("An", "Espresso", 1));
        Optional<Order> found = findUrgent(orders, "An");
        Optional<Order> missing = findUrgent(orders, "Zed");

        System.out.println(found.isPresent() + " " + found.get().drink);
        // map không chạy trên hộp rỗng, orElse cho giá trị dự phòng
        System.out.println(missing.isPresent() + " " + missing.map(o -> o.drink).orElse("none"));
        found.ifPresent(o -> System.out.println("serve " + o.drink));
    }
}
