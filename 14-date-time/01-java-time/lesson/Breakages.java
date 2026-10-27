import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Breakages {
    public static void main(String[] args) {
        try {
            // Tháng 2 không có ngày 30
            LocalDate.of(2026, 2, 30);
        } catch (java.time.DateTimeException e) {
            System.out.println("1: " + e);
        }

        LocalDate day = LocalDate.of(2026, 8, 14);
        // Kết quả không được gán nên day không đổi
        day.plusDays(1);
        System.out.println("2: " + day);

        try {
            LocalDate.parse("2026-12-25", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("3: " + e.getMessage());
        }

        try {
            // Duration cần giờ nên LocalDate không dùng được
            Duration.between(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 2));
        } catch (java.time.temporal.UnsupportedTemporalTypeException e) {
            System.out.println("4: " + e);
        }
    }
}
