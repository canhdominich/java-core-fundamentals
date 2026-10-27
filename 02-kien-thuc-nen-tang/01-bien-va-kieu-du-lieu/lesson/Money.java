import java.math.BigDecimal;

public class Money {
    public static void main(String[] args) {
        double sum = 0.1 + 0.2; // hai số nhị phân gần đúng nên tổng lệch một chút
        System.out.println("double: " + sum + " equals 0.3: " + (sum == 0.3));
        BigDecimal exact = new BigDecimal("0.1").add(new BigDecimal("0.2")); // tạo từ chuỗi để giữ đúng chữ số
        System.out.println("BigDecimal: " + exact);
    }
}
