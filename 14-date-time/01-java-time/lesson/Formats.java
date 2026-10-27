import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formats {
    public static void main(String[] args) {
        // Tạo một lần và giữ làm hằng, formatter không bao giờ đổi
        DateTimeFormatter slash = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expiry = LocalDate.of(2026, 2, 28);
        String text = expiry.format(slash);
        // Đọc chuỗi theo đúng mẫu thì ra ngày thật
        LocalDate back = LocalDate.parse("25/12/2026", slash);
        System.out.println(text + " | " + back + " | " + back.equals(LocalDate.of(2026, 12, 25)));

        LocalDate edge = LocalDate.of(2025, 12, 29);
        System.out.println(edge.format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)));
        // YYYY là năm theo tuần, cuối tháng 12 có thể ra năm sau
        System.out.println(edge.format(DateTimeFormatter.ofPattern("YYYY-MM-dd", Locale.US)));
    }
}
