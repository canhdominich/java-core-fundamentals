import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class FixedClock {
    // Nhận Clock vào để test được mà không chờ thời gian thật
    static boolean expired(LocalDate deadline, Clock clock) {
        return LocalDate.now(clock).isAfter(deadline);
    }

    public static void main(String[] args) {
        LocalDate deadline = LocalDate.of(2026, 8, 14);
        Clock onDeadline = Clock.fixed(Instant.parse("2026-08-14T23:59:00Z"), ZoneOffset.UTC);
        Clock nextDay = Clock.fixed(Instant.parse("2026-08-15T00:01:00Z"), ZoneOffset.UTC);
        System.out.println(expired(deadline, onDeadline) + " " + expired(deadline, nextDay));
    }
}
