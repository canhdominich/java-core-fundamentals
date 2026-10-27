public class Order {
    public static void main(String[] args) {
        System.out.println(2 + 3 * 4); // phép nhân đi trước phép cộng
        System.out.println((2 + 3) * 4); // dấu ngoặc luôn đi trước
        System.out.println("1" + 2 + 3); // có chuỗi từ phép đầu nên dấu cộng ghép chữ
        System.out.println(1 + 2 + "3"); // hai số đứng trước chuỗi nên được cộng thật
        System.out.println(7 / 2 * 2.0); // cùng cấp, trái sang phải: chia nguyên trước
        System.out.println(7 / (2 * 2.0)); // ngoặc biến phép chia thành chia thực
    }
}
