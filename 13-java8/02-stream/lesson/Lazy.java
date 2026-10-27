import java.util.List;
import java.util.Optional;

public class Lazy {
    static class Order {
        final String drink;
        final int priority;

        Order(String drink, int priority) {
            this.drink = drink;
            this.priority = priority;
        }
    }

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Latte", 2), new Order("Mocha", 1),
                new Order("Tea", 3), new Order("Espresso", 1));

        // Chỉ dựng đường ống: chưa có thao tác kết thúc nên không in gì
        orders.stream()
                .peek(o -> System.out.println("source " + o.drink))
                .filter(o -> o.priority == 1);
        System.out.println("built, nothing ran");

        // findFirst là thao tác kết thúc, và nó dừng ngay khi có kết quả
        Optional<String> first = orders.stream()
                .peek(o -> System.out.println("source " + o.drink))
                .filter(o -> o.priority == 1)
                .map(o -> o.drink)
                .findFirst();
        System.out.println("first: " + first.get());
    }
}
