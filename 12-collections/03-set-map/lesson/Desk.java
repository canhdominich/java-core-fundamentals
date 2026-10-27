import java.util.*;

public class Desk {
    public static void main(String[] args) {
        Set<String> seen = new HashSet<>();
        boolean a = seen.add("A100");
        boolean b = false;
        boolean c = false;
        b = seen.add("A101");
        c = seen.add("A100");  // đã có nên false
        System.out.println("step 1: " + a + " " + b + " " + c);

        Map<Integer, String> byCustomer = new HashMap<>();
        byCustomer.put(7, "Latte");
        byCustomer.put(8, "Mocha");
        System.out.println("step 2: " + byCustomer.get(7) + " " + byCustomer.size());

        String old = null;
        old = byCustomer.put(7, "Tea");  // put trả về giá trị cũ là Latte
        System.out.println("step 3: " + old + " " + byCustomer.get(7) + " " + byCustomer.size());

        String none = null;
        none = byCustomer.getOrDefault(9, "none");  // khoá 9 không có
        System.out.println("step 4: " + none + " " + byCustomer.containsKey(9));

        // TreeMap luôn giữ khoá theo thứ tự abc
        Map<String, Integer> price = new TreeMap<>();
        price.put("Tea", 30);
        price.put("Latte", 45);
        price.put("Mocha", 50);
        System.out.println("step 5: " + price);
    }
}
