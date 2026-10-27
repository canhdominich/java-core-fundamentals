import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Capture {
    public static void main(String[] args) {
        int limit = 6;
        Predicate<Integer> big = n -> n > limit;  // lambda giữ một bản sao của limit
        List<Integer> nums = new ArrayList<>(List.of(5, 8, 3, 10));
        nums.removeIf(big);
        System.out.println(nums);

        // Đối tượng được bắt giữ vẫn sửa được nội dung, chỉ không gán lại biến
        StringBuilder log = new StringBuilder();
        nums.forEach(n -> log.append(n).append(';'));
        System.out.println(log);
    }
}
