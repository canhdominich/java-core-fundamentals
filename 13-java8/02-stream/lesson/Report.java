import java.util.*;
import java.util.stream.*;

public class Report {
    public static void main(String[] args) {
        List<String> names = List.of("Latte", "Tea", "Mocha", "Chai");
        // filter giữ tên dài hơn 3, collect gom kết quả vào List
        List<String> longNames = names.stream().filter(s -> s.length() > 3).collect(Collectors.toList());
        System.out.println("step 1: " + longNames);

        // map đổi mỗi tên thành độ dài của nó
        List<Integer> lengths = names.stream().map(s -> s.length()).collect(Collectors.toList());
        System.out.println("step 2: " + lengths);

        List<Integer> nums = List.of(5, 3, 8, 1, 9);
        // sorted rồi limit: lấy 3 số nhỏ nhất
        List<Integer> smallest = nums.stream().sorted().limit(3).collect(Collectors.toList());
        System.out.println("step 3: " + smallest);

        // mapToInt đổi sang luồng số nguyên để có sum()
        int total = nums.stream().mapToInt(n -> n).sum();
        System.out.println("step 4: " + total);

        // groupingBy gom theo độ dài, counting đếm mỗi nhóm
        Map<Integer, Long> byLength = names.stream().collect(Collectors.groupingBy(s -> s.length(), Collectors.counting()));
        System.out.println("step 5: " + new TreeMap<>(byLength));  // TreeMap để in theo thứ tự khoá
    }
}
