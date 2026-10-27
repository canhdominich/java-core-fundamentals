import java.util.*;
import java.util.function.*;

public class Check44 {
    static class Order {
        final String drink; final int priority;
        Order(String d, int p) { drink = d; priority = p; }
        public String toString() { return drink + ":" + priority; }
    }
    public static void main(String[] args) {
        List<Order> orders = List.of(new Order("Latte", 2), new Order("Mocha", 1), new Order("Tea", 3), new Order("Espresso", 1), new Order("Latte", 1));
        Predicate<Order> urgent = o -> o.priority == 1;
        Predicate<Order> latte = o -> o.drink.equals("Latte");
        Predicate<Order> rule = urgent.and(latte.negate());
        int n = 0;
        for (Order o : orders) if (rule.test(o)) n++;
        System.out.println(n);
        Predicate<Order> isTea = o -> o.drink.equals("Tea");
        Function<Order, String> up = o -> o.drink.toUpperCase();
        Consumer<Order> show = o -> System.out.println("screen: " + o);
        Supplier<Order> def = () -> new Order("Water", 3);
        System.out.println(isTea.test(orders.get(2)) + " " + up.apply(orders.get(0)) + " " + def.get());
        show.accept(orders.get(1));
        int total = 0;
        for (Order o : orders) { total += o.priority; }
        System.out.println(total);
        int[] calls = {0};
        Predicate<Order> counted = o -> { calls[0]++; return o.priority == 1; };
        for (Order o : orders) { if (counted.test(o)) { Function<Order, String> l = x -> { calls[0]++; return x.drink; }; l.apply(o); } }
        System.out.println(calls[0]);
    }
}
