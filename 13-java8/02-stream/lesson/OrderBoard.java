import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class OrderBoard {
    static class Order {
        final String drink;
        final int priority;
        final int price;

        Order(String drink, int priority, int price) {
            this.drink = drink;
            this.priority = priority;
            this.price = price;
        }
    }

    private final List<Order> orders = new ArrayList<>();

    public void add(Order order) {
        orders.add(order);
    }

    public List<String> urgentLabels() {
        return orders.stream()
                .filter(o -> o.priority == 1)
                .map(o -> o.drink)
                .collect(Collectors.toList());
    }

    public int revenue() {
        return orders.stream().mapToInt(o -> o.price).sum();
    }

    public Map<String, Long> countByDrink() {
        // Bọc vào TreeMap để kết quả in ra theo thứ tự abc
        return new TreeMap<>(orders.stream().collect(Collectors.groupingBy(o -> o.drink, Collectors.counting())));
    }

    public static void main(String[] args) {
        OrderBoard board = new OrderBoard();
        board.add(new Order("Latte", 2, 45));
        board.add(new Order("Mocha", 1, 50));
        board.add(new Order("Tea", 3, 30));
        board.add(new Order("Espresso", 1, 40));
        board.add(new Order("Latte", 1, 45));
        System.out.println(board.urgentLabels());
        System.out.println(board.revenue());
        System.out.println(board.countByDrink());
    }
}
