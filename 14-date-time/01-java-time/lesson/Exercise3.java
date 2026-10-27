import java.time.LocalDate;

public class Exercise3 {
    public static void main(String[] args) {
        LocalDate expiry = LocalDate.of(2026, 2, 28);
        // Gán lại để biến trỏ tới ngày mới
        expiry = expiry.plusDays(10);
        System.out.println(expiry);
    }
}
