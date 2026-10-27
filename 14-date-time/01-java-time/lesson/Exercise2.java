import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Exercise2 {
    public static void main(String[] args) {
        LocalDate birth = LocalDate.of(2001, 9, 5);
        LocalDate today = LocalDate.of(2026, 8, 14);
        Period age = Period.between(birth, today);
        System.out.println(birth.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println(age.getYears() + " years " + age.getMonths() + " months " + age.getDays() + " days");
    }
}
