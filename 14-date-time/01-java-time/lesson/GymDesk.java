import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class GymDesk {
    public static void main(String[] args) {
        LocalDate bought = LocalDate.of(2026, 1, 31);
        // Hạn thẻ một tháng, ngày 31 lùi về cuối tháng
        LocalDate expiry = bought.plusMonths(1);
        System.out.println("step 1: " + expiry);

        LocalDate renewed = expiry.plusMonths(1);
        // Cộng hai tháng một lần từ ngày gốc
        LocalDate direct = bought.plusMonths(2);
        System.out.println("step 2: " + renewed + " " + direct);

        LocalDate today = LocalDate.of(2026, 2, 10);
        // Đầu viết trước, cuối viết sau
        long daysLeft = ChronoUnit.DAYS.between(today, expiry);
        System.out.println("step 3: " + daysLeft);

        // Dùng yyyy thường, không dùng YYYY
        String printed = expiry.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("step 4: " + printed);

        Instant issued = Instant.parse("2026-01-31T02:30:00Z");
        // Đổi mốc UTC sang giờ treo tường ở Hồ Chí Minh
        ZonedDateTime local = issued.atZone(ZoneId.of("Asia/Ho_Chi_Minh"));
        System.out.println("step 5: " + local);
    }
}
