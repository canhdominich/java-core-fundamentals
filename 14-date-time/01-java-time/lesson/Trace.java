import java.time.LocalDate;

public class Trace {
    public static void main(String[] args) {
        LocalDate p = LocalDate.of(2026, 1, 30);
        // Không gán nên bị bỏ đi
        p.plusMonths(1);
        LocalDate q = p.plusMonths(1);
        LocalDate r = q.plusDays(3);
        LocalDate s = p.plusMonths(2);
        System.out.println(p + " " + q + " " + r + " " + s);
    }
}
