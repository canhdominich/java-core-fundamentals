import java.util.*;

public class Clean {
    public static void main(String[] args) {
        List<String> orders = new ArrayList<>(List.of("Tea", "Latte", "Tea", "Mocha", "Latte"));
        List<String> unique = new ArrayList<>(new LinkedHashSet<>(orders));
        List<String> sorted = new ArrayList<>(new TreeSet<>(orders));
        List<String> frozen = List.copyOf(unique);
        System.out.println("unique " + unique);
        System.out.println("sorted " + sorted);
        try {
            frozen.add("X");
        } catch (UnsupportedOperationException e) {
            System.out.println("frozen add: UnsupportedOperationException");
        }
        List<String> src = new ArrayList<>(List.of("A", "B"));
        List<String> view = Collections.unmodifiableList(src);
        List<String> copy = List.copyOf(src);
        src.add("C");
        src.remove("A");
        System.out.println(view + " " + copy);
        List<String> menu = new ArrayList<>(List.of("Latte", "Mocha"));
        menu.add("Tea");
        System.out.println(menu);
    }
}
