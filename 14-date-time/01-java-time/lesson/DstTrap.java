import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DstTrap {
    public static void main(String[] args) {
        ZoneId ny = ZoneId.of("America/New_York");
        ZonedDateTime start = ZonedDateTime.of(2026, 3, 7, 12, 0, 0, 0, ny);
        // Cộng theo lịch: giữ nguyên giờ treo tường
        System.out.println(start.plus(Period.ofDays(1)).toLocalDateTime());
        // Cộng đúng 24 giờ trôi qua
        System.out.println(start.plus(Duration.ofHours(24)).toLocalDateTime());

        // Giờ 02:30 không tồn tại nên bị đẩy tới 03:30
        ZonedDateTime missing = ZonedDateTime.of(LocalDateTime.of(2026, 3, 8, 2, 30), ny);
        System.out.println(missing.toLocalDateTime() + " " + missing.getOffset());
    }
}
