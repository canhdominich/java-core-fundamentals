import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BoardLambda {
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

    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>(List.of(
                new Order("Latte", 2), new Order("Mocha", 1), new Order("Tea", 3), new Order("Espresso", 1)));

        // Bốn lambda này chỉ được tạo ra, thân của chúng chưa chạy
        Predicate<Order> urgent = o -> o.priority == 1;
        Function<Order, String> label = o -> o.drink.toUpperCase();
        Consumer<String> show = s -> System.out.println("screen: " + s);
        Supplier<Order> fallback = () -> new Order("Water", 3);

        for (Order o : orders) {
            if (urgent.test(o)) {
                show.accept(label.apply(o));  // thân lambda chạy ở đây, mỗi lần gọi một lần
            }
        }
        orders.removeIf(urgent.negate());  // negate đảo điều kiện: xoá đơn không khẩn
        System.out.println(orders + " " + fallback.get());
    }
}
