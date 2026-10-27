import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderBoard {
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

    private final List<Order> orders = new ArrayList<>();

    public void add(Order order) {
        orders.add(order);
    }

    public Optional<Order> findUrgent(String customer) {
        return orders.stream()
                .filter(o -> o.customer.equals(customer) && o.priority == 1)
                .findFirst();
    }

    public String drinkFor(String customer) {
        // map lấy tên đồ uống nếu có đơn, không có thì dùng giá trị dự phòng
        return findUrgent(customer).map(o -> o.drink).orElse("none");
    }

    public Order mustFindUrgent(String customer) {
        return findUrgent(customer).orElseThrow(() -> new IllegalArgumentException("no urgent order for " + customer));
    }

    public static void main(String[] args) {
        OrderBoard board = new OrderBoard();
        board.add(new Order("An", "Latte", 2));
        board.add(new Order("Binh", "Mocha", 1));
        board.add(new Order("An", "Espresso", 1));
        System.out.println(board.findUrgent("An").isPresent());
        System.out.println(board.drinkFor("Binh") + " " + board.drinkFor("Zed"));
        System.out.println(board.mustFindUrgent("An").drink);
        try {
            board.mustFindUrgent("Zed");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
