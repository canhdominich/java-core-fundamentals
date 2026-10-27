import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class OrderBoard {
    static class Order {
        final String drink;
        final int priority;

        Order(String drink, int priority) {
            this.drink = drink;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return drink + ":" + priority;
        }
    }

    private final List<Order> orders = new ArrayList<>();

    public void add(Order order) {
        orders.add(order);
    }

    // Nhận điều kiện từ bên ngoài, đếm các đơn thoả điều kiện đó
    public int count(Predicate<Order> rule) {
        int n = 0;
        for (Order o : orders) {
            if (rule.test(o)) {
                n++;
            }
        }
        return n;
    }

    public List<String> labels(Function<Order, String> toLabel) {
        List<String> out = new ArrayList<>();
        for (Order o : orders) {
            out.add(toLabel.apply(o));
        }
        return out;
    }

    public void forEachOrder(Consumer<Order> action) {
        for (Order o : orders) {
            action.accept(o);
        }
    }

    public static void main(String[] args) {
        OrderBoard board = new OrderBoard();
        board.add(new Order("Latte", 2));
        board.add(new Order("Mocha", 1));
        board.add(new Order("Tea", 3));
        board.add(new Order("Espresso", 1));
        System.out.println(board.count(o -> o.priority == 1));
        System.out.println(board.labels(o -> o.drink + "#" + o.priority));
        board.forEachOrder(o -> System.out.println("screen: " + o));
    }
}
