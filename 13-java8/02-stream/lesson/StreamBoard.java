import java.util.List;
import java.util.stream.Collectors;

public class StreamBoard {
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

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Latte", 2, 45), new Order("Mocha", 1, 50),
                new Order("Tea", 3, 30), new Order("Espresso", 1, 40));

        // Nguồn, hai thao tác trung gian, rồi một thao tác kết thúc
        List<String> urgentDrinks = orders.stream()
                .filter(o -> o.priority == 1)
                .map(o -> o.drink)
                .collect(Collectors.toList());
        System.out.println("urgent: " + urgentDrinks);

        // mapToInt đổi sang luồng số nguyên để có sum()
        int revenue = orders.stream().mapToInt(o -> o.price).sum();
        System.out.println("revenue: " + revenue + ", source still " + orders.size() + " orders");
    }
}
