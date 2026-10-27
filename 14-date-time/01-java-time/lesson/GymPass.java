import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class GymPass {
    public static void main(String[] args) {
        LocalDate bought = LocalDate.of(2026, 1, 31);
        // Không gán kết quả nên ngày mới bị bỏ đi, bought không đổi
        bought.plusDays(30);
        // Tháng 2 không có ngày 31 nên lùi về ngày 28
        LocalDate first = bought.plusMonths(1);
        // Cộng từ 28/2 chứ không phải từ 31/1
        LocalDate second = first.plusMonths(1);
        // Cộng một lần từ ngày gốc nên giữ được ngày 31
        LocalDate direct = bought.plusMonths(2);
        System.out.println("bought " + bought);
        System.out.println("first  " + first);
        System.out.println("second " + second);
        System.out.println("direct " + direct);
        // Đếm tổng số ngày giữa hai mốc
        System.out.println("days from bought to second " + ChronoUnit.DAYS.between(bought, second));
    }
}
