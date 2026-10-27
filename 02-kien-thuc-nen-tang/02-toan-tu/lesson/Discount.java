public class Discount {
    public static void main(String[] args) {
        int price = 1_500_000;
        int wrong = price * (10 / 100); // 10 / 100 là chia nguyên nên bằng 0
        int right = price * 10 / 100; // nhân trước, chia sau thì phần lẻ không rơi mất
        double exact = price * 0.10; // 0.10 là double nên phép nhân là phép thực
        System.out.println("wrong = " + wrong);
        System.out.println("right = " + right);
        System.out.println("exact = " + exact);
        System.out.println("7 / 2 = " + (7 / 2) + ", 7 / 2.0 = " + (7 / 2.0));
        System.out.println("7 % 3 = " + (7 % 3) + ", -7 % 3 = " + (-7 % 3)); // phần dư mang dấu của số bị chia
    }
}
