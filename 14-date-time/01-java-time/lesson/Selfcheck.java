import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Selfcheck {
    public static void main(String[] args) {
        LocalDate a = LocalDate.of(2026, 3, 31);
        // Lùi một tháng từ 31/3, ngày lùi về cuối tháng 2
        System.out.println(a.minusMonths(1));
        System.out.println(a.minusMonths(1).plusMonths(1));
        System.out.println(Period.between(LocalDate.of(2026, 1, 15), LocalDate.of(2026, 3, 10)));
        LocalDate d = LocalDate.of(2026, 5, 1);
        LocalDate e = d.plusDays(5).minusDays(2);
        System.out.println(d + " " + e);
        // Năm theo tuần
        System.out.println(LocalDate.of(2025, 12, 29).format(DateTimeFormatter.ofPattern("dd/MM/YYYY", Locale.US)));
        System.out.println(Instant.parse("2026-12-31T20:00:00Z").atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDate());
    }
}
