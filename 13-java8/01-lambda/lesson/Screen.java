import java.util.*;
import java.util.function.*;

public class Screen {
    public static void main(String[] args) {
        Predicate<String> isLong = s -> s.length() > 4;
        System.out.println("step 1: " + isLong.test("Tea") + " " + isLong.test("Mocha"));

        Function<String, Integer> len = s -> s.length();
        System.out.println("step 2: " + len.apply("Latte"));

        List<String> names = new ArrayList<>(List.of("Latte", "Tea", "Mocha"));
        StringBuilder initials = new StringBuilder();
        names.forEach(n -> initials.append(n.charAt(0)));
        System.out.println("step 3: " + initials);

        int limit = 2;
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4));
        nums.removeIf(n -> n > limit);
        System.out.println("step 4: " + nums);

        names.sort((a, b) -> Integer.compare(b.length(), a.length()));
        System.out.println("step 5: " + names);
    }
}
