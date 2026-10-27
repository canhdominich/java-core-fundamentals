import java.util.*;

public class Board {
    public static void main(String[] args) {
        List<String> orders = new ArrayList<>(List.of("Latte", "CANCELLED", "Mocha", "CANCELLED", "Tea"));
        Iterator<String> it = orders.iterator();
        while (it.hasNext()) {
            if (it.next().equals("CANCELLED")) {
                it.remove();
            }
        }
        System.out.println("step 1: " + orders);

        List<String> names = new ArrayList<>(List.of("Binh", "An", "Dung", "Chi"));
        // Dùng lại byLength ở bước 2, 3 và 5
        Comparator<String> byLength = Comparator.comparingInt(s -> s.length());
        names.sort(byLength.thenComparing(Comparator.naturalOrder()));
        System.out.println("step 2: " + names);

        names.sort(byLength.reversed());
        System.out.println("step 3: " + names);

        List<Integer> nums = new ArrayList<>(List.of(Integer.MAX_VALUE, -5, 0, Integer.MIN_VALUE));
        nums.sort((x, y) -> Integer.compare(x, y));  // không dùng phép trừ vì có thể tràn
        System.out.println("step 4: " + nums);

        // Thêm tiêu chí tên để Binh và Dung không bị coi là trùng
        Set<String> set = new TreeSet<>(byLength.thenComparing(Comparator.naturalOrder()));
        set.addAll(names);
        System.out.println("step 5: " + set.size());
    }
}
