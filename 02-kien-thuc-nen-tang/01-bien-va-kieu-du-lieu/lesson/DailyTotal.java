import java.math.BigDecimal;

public class DailyTotal {
    public static void main(String[] args) {
        long fans = 2_000L * 1_500_000; // hậu tố L để phép nhân chạy trong long
        long kettles = 5_000L * 320_000;
        long cups = 40_000L * 12_000;
        long revenue = fans + kettles + cups;
        BigDecimal vat = new BigDecimal(revenue).multiply(new BigDecimal("0.08")); // 0.08 tạo từ chuỗi
        System.out.println("revenue = " + revenue);
        System.out.println("VAT = " + vat);
    }
}
