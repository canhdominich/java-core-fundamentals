import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Strict {
    public static void main(String[] args) {
        DateTimeFormatter smart = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        // Chế độ STRICT từ chối ngày không có thật
        DateTimeFormatter strict = smart.withResolverStyle(ResolverStyle.STRICT);
        System.out.println(LocalDate.parse("31/02/2026", smart));
        try {
            LocalDate.parse("31/02/2026", strict);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
