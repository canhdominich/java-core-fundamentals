import java.math.BigDecimal;

public class Breakages {
    public static void main(String[] args) {
        try {
            Math.multiplyExact(2_000, 1_500_000); // báo lỗi thay vì trả số sai
        } catch (ArithmeticException e) {
            System.out.println("1: " + e);
        }
        String name = null;
        try {
            name.length(); // gọi qua tờ phiếu trống
        } catch (NullPointerException e) {
            System.out.println("2: " + e.getMessage());
        }
        System.out.println("3: " + (int) 3.99 + " " + (int) -3.99 + " " + (int) 1e20); // ép sang int cắt phần lẻ về phía 0
        System.out.println("4: " + new BigDecimal(0.1)); // mang theo sai số của double
        System.out.println("5: " + new BigDecimal("0.1")); // đúng chữ số đã gõ
    }
}
