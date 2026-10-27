import java.util.*;
import java.util.function.*;
public class Facts46 {
    static class Order { final String note; Order(String n) { note = n; } }
    public static void main(String[] args) {
        Optional<String> a = Optional.of("Latte");
        Optional<String> b = a.filter(s -> s.length() > 5);
        Optional<String> c = a.map(s -> s.toUpperCase());
        Optional<String> d = b.map(s -> s.toUpperCase());
        System.out.println(a + " " + b + " " + c + " " + d + " " + a.isPresent() + b.isPresent() + c.isPresent() + d.isPresent());
        try { Optional.of(null); } catch (NullPointerException e) { System.out.println("NPE"); }
        try { Optional.empty().get(); } catch (NoSuchElementException e) { System.out.println("NSEE " + e.getMessage()); }
        for (String n : new String[] {null, "extra hot", "!now"}) {
            System.out.println(Optional.ofNullable(n).map(String::length).orElse(-1) + " " + Optional.ofNullable(n).filter(s -> s.startsWith("!")).map(s -> "rush").orElse("normal"));
        }
        System.out.println(Optional.of("Latte").filter(s -> s.startsWith("M")).map(String::length).orElse(-1));
        System.out.println(Optional.of("a").equals(Optional.of("a")) + " " + Optional.empty().isEmpty());
        System.out.println(Optional.of("x").stream().count() + " " + Optional.empty().stream().count());
        Optional<String> nested = Optional.of("An").flatMap(x -> Optional.of(x + "!"));
        System.out.println(nested);
    }
}
