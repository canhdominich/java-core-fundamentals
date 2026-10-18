package dev.canhdominich.core.java8.stream;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class Pipelines {

    private Pipelines() {
    }

    public static List<Order> orders() {
        return List.of(
                new Order("An", "pen", 10, 5_000),
                new Order("Binh", "book", 2, 80_000),
                new Order("An", "book", 1, 80_000),
                new Order("Chi", "pen", 4, 5_000),
                new Order("Binh", "bag", 1, 250_000),
                new Order("Chi", "book", 3, 80_000));
    }

    public static String basicPipeline() {
        List<String> bigCustomers = orders().stream()
                .filter(order -> order.total() >= 100_000)
                .map(Order::customer)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        long revenue = orders().stream().mapToLong(Order::total).sum();
        return "customers with an order of at least 100000: " + bigCustomers + ", revenue " + revenue;
    }

    public static String laziness() {
        List<String> trace = new ArrayList<>();
        Stream<Integer> pipeline = Stream.of(1, 2, 3, 4, 5, 6)
                .peek(n -> trace.add("saw " + n))
                .filter(n -> n % 2 == 0);
        int afterBuilding = trace.size();
        Optional<Integer> first = pipeline.findFirst();
        return "building the pipeline touched " + afterBuilding + " elements, findFirst returned " + first.orElse(-1) + " after seeing " + trace.size() + " elements";
    }

    public static String reuseFails() {
        Stream<String> stream = Stream.of("a", "b");
        stream.count();
        try {
            stream.count();
            return "no exception";
        } catch (IllegalStateException e) {
            return "a stream cannot be reused: " + e.getMessage();
        }
    }

    public static String groupingAndPartitioning() {
        Map<String, Long> ordersPerCustomer = new TreeMap<>(orders().stream().collect(Collectors.groupingBy(Order::customer, Collectors.counting())));
        Map<String, Long> revenuePerCustomer = new TreeMap<>(orders().stream().collect(Collectors.groupingBy(Order::customer, Collectors.summingLong(Order::total))));
        Map<Boolean, List<String>> bigOrSmall = orders().stream()
                .collect(Collectors.partitioningBy(order -> order.total() >= 100_000, Collectors.mapping(order -> order.customer() + "/" + order.product(), Collectors.toList())));
        return "orders per customer " + ordersPerCustomer + ", revenue per customer " + revenuePerCustomer + ", big " + bigOrSmall.get(true) + ", small " + bigOrSmall.get(false);
    }

    public static String toMapAndDuplicates() {
        try {
            orders().stream().collect(Collectors.toMap(Order::customer, Order::total));
            return "no exception";
        } catch (IllegalStateException e) {
            Map<String, Long> merged = new TreeMap<>(orders().stream().collect(Collectors.toMap(Order::customer, Order::total, Long::sum)));
            return "toMap with duplicate keys throws IllegalStateException (" + e.getMessage().substring(0, 13) + "...), with a merge function it gives " + merged;
        }
    }

    public static String primitiveStreams() {
        int sum = IntStream.rangeClosed(1, 100).sum();
        IntSummaryStatistics stats = IntStream.of(3, 9, 4, 1).summaryStatistics();
        double average = IntStream.of(1, 2, 3, 4).average().orElse(0);
        List<Integer> boxed = IntStream.range(0, 5).boxed().collect(Collectors.toList());
        return "sum 1 to 100 is " + sum + ", min " + stats.getMin() + ", max " + stats.getMax() + ", average " + average + ", boxed " + boxed;
    }

    public static String joiningAndFlatMap() {
        List<List<String>> nested = List.of(List.of("a", "b"), List.of(), List.of("c"));
        List<String> flat = nested.stream().flatMap(List::stream).collect(Collectors.toList());
        String joined = Stream.of("x", "y", "z").collect(Collectors.joining(", ", "[", "]"));
        return "flattened " + flat + ", joined " + joined + ", words from sentences " + Stream.of("to be", "or not").flatMap(s -> Stream.of(s.split(" "))).collect(Collectors.toList());
    }

    public static String listsFromStreams() {
        List<Integer> mutable = Stream.of(1, 2).collect(Collectors.toList());
        mutable.add(3);
        List<Integer> immutable = Stream.of(1, 2).toList();
        String result;
        try {
            immutable.add(3);
            result = "added";
        } catch (UnsupportedOperationException e) {
            result = "UnsupportedOperationException";
        }
        return "Collectors.toList can be extended " + mutable + ", Stream.toList add: " + result;
    }

    public static String sourceIsNotModified() {
        List<Integer> source = new ArrayList<>(List.of(3, 1, 2));
        List<Integer> sorted = source.stream().sorted().collect(Collectors.toList());
        return "source " + source + ", sorted copy " + sorted;
    }
}
