import java.util.Scanner;

public class ReceiptPrinter {
    public static void main(String[] args) {
        Scanner in = new Scanner("Rice\n3\n18500.5\n"); // ba dòng: tên, số lượng, đơn giá
        String item = in.nextLine();
        int quantity = Integer.parseInt(in.nextLine());
        double price = Double.parseDouble(in.nextLine());
        System.out.printf("%-10s|%5s|%10s%n", "Item", "Qty", "Total"); // tiêu đề dùng %s cho chữ
        System.out.printf("%-10s|%5d|%10.2f%n", item, quantity, quantity * price);
    }
}
