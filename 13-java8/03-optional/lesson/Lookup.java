import java.util.Optional;

public class Lookup {
    public static void main(String[] args) {
        // of cần giá trị chắc chắn không null, ofNullable chấp nhận null
        Optional<String> a = Optional.of("Latte");
        Optional<String> b = Optional.ofNullable(null);
        System.out.println("step 1: " + a + " " + b);

        String drink = b.orElse("none");  // hộp rỗng nên lấy giá trị dự phòng
        System.out.println("step 2: " + drink);

        // Chỉ chạy lambda khi hộp có giá trị
        a.ifPresent(d -> System.out.println("step 3: " + d));

        // map không chạy trên hộp rỗng nên lenB lấy giá trị dự phòng 0
        int lenA = a.map(s -> s.length()).orElse(0);
        int lenB = b.map(s -> s.length()).orElse(0);
        System.out.println("step 4: " + lenA + " " + lenB);

        try {
            // Hộp rỗng thì ném đúng ngoại lệ do mình chọn
            b.orElseThrow(() -> new IllegalStateException("no drink"));
        } catch (IllegalStateException e) {
            System.out.println("step 5: " + e.getMessage());
        }
    }
}
