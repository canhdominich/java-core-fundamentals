import java.util.*;
import java.util.stream.*;

public class Check45 {
    static class Order {
        final String drink; final int priority; final int price;
        Order(String d, int p, int pr) { drink = d; priority = p; price = pr; }
        public String toString() { return drink + ":" + priority; }
    }
    public static void main(String[] args) {
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6).peek(n -> System.out.print("seen" + n + " ")).filter(n -> n % 2 == 0).map(n -> n * n).limit(2).collect(Collectors.toList()));
        List<Order> orders = List.of(new Order("Latte", 2, 45), new Order("Mocha", 1, 50), new Order("Tea", 3, 30), new Order("Espresso", 1, 40), new Order("Latte", 1, 45));
        System.out.println(orders.stream().filter(o -> o.price > 40).count());
        System.out.println(orders.stream().map(o -> o.drink).distinct().sorted().collect(Collectors.joining(", ")));
        System.out.println(orders.stream().anyMatch(o -> o.priority == 3));
        System.out.println(new TreeMap<>(orders.stream().collect(Collectors.groupingBy(o -> o.drink, Collectors.counting()))));
        System.out.println(new TreeMap<>(orders.stream().collect(Collectors.groupingBy(o -> o.drink, Collectors.summingInt(o -> o.price)))));
        System.out.println(orders.stream().sorted(Comparator.comparingInt((Order o) -> o.price).reversed()).limit(2).map(o -> o.drink).collect(Collectors.toList()));
        Stream<String> s = Stream.of("a", "b");
        s.count();
        try { s.collect(Collectors.toList()); } catch (IllegalStateException e) { System.out.println("ISE " + e.getMessage()); }
        try { Stream.of("a", "a").collect(Collectors.toMap(x -> x, x -> 1)); } catch (IllegalStateException e) { System.out.println("ISE " + e.getMessage()); }
        try { Stream.of("a").toList().add("b"); } catch (UnsupportedOperationException e) { System.out.println("UOE " + e.getMessage()); }
        Stream<Integer> st = Stream.of(1, 2, 3).map(n -> { System.out.print("m" + n + " "); return n * 2; });
        System.out.print("start ");
        System.out.println(st.collect(Collectors.toList()));
        System.out.println(IntStream.rangeClosed(1, 100).sum());
        System.out.println(Stream.of("to be", "or not").flatMap(x -> Arrays.stream(x.split(" "))).collect(Collectors.toList()));
        System.out.println(Stream.of("a", "b").collect(Collectors.toList()).getClass().getSimpleName());
        System.out.println(orders.stream().filter(o -> o.priority == 1).map(o -> o.drink).collect(Collectors.toList()));
        System.out.println(orders.stream().filter(o -> o.priority == 1).mapToInt(o -> o.price).sum());
        System.out.println(orders.stream().mapToInt(o -> o.price).max().getAsInt() + " " + orders.stream().mapToInt(o -> o.price).average().getAsDouble());
        System.out.println(Stream.of("b", "a", "c").map(x -> x.toUpperCase()).sorted().collect(Collectors.toList()));
        System.out.println(new TreeMap<>(Stream.of("Tea", "Latte", "Mocha", "Chai").collect(Collectors.groupingBy(String::length, Collectors.counting()))));
    }
}
