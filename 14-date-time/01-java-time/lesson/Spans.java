import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Spans {
    public static void main(String[] args) {
        LocalDate birth = LocalDate.of(1997, 3, 20);
        LocalDate today = LocalDate.of(2026, 8, 14);
        // Tuổi tính theo lịch: năm, tháng, ngày
        Period age = Period.between(birth, today);
        System.out.println(age + " = " + age.getYears() + " years " + age.getMonths() + " months " + age.getDays() + " days");
        // Tổng số ngày, không tự chia cho 365
        System.out.println("days between " + ChronoUnit.DAYS.between(birth, today));
        LocalTime open = LocalTime.of(9, 0);
        LocalTime close = LocalTime.of(11, 30);
        // Duration đo giờ phút nên dùng với LocalTime
        Duration open2close = Duration.between(open, close);
        System.out.println(open2close + " = " + open2close.toMinutes() + " minutes");
    }
}
