import java.util.Scanner;

public class Checkout {
    public static void main(String[] args) {
        Scanner in = new Scanner("25\nAn Binh\n"); // giả lập bàn phím
        int quantity = Integer.parseInt(in.nextLine());
        String name = in.nextLine();
        System.out.println("step 1: " + quantity + " " + name);
        System.out.printf("step 2: %-10s|%5d%n", name, quantity);
        double price = 19999.5;
        System.out.printf("step 3: %.2f%n", price * quantity);
        Scanner two = new Scanner("abc 7");
        boolean first = two.hasNextInt();
        two.next(); // bỏ token sai
        boolean second = two.hasNextInt();
        System.out.println("step 4: " + first + " " + second);
        String line = String.format("%-10s|%5d", name, quantity); // String.format tạo chuỗi, không in
        System.out.println("step 5: [" + line + "]");
    }
}
