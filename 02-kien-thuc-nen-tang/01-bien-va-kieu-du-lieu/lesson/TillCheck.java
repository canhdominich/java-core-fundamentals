import java.math.BigDecimal;

public class TillCheck {
    public static void main(String[] args) {
        String item = "Mini Fan";
        int quantity = 2_000;
        int price = 1_500_000;
        System.out.println("step 1: " + item + " " + quantity + " " + price);
        int total = quantity * price; // cố ý để tràn int
        System.out.println("step 2: " + total);
        long safeTotal = (long) quantity * price; // ép trước khi nhân
        System.out.println("step 3: " + safeTotal);
        BigDecimal exact = new BigDecimal("0.1").add(new BigDecimal("0.2"));
        System.out.println("step 4: " + (0.1 + 0.2) + " " + exact);
        int[] stock = {10};
        int[] alias = stock; // hai tên cho cùng một mảng
        alias[0] = 99;
        System.out.println("step 5: " + stock[0]);
    }
}
