public class PrintDemo {
    public static void main(String[] args) {
        String name = "An Binh";
        int quantity = 25;
        double price = 19999.5;
        System.out.print("Customer: "); // print không xuống dòng
        System.out.println(name); // println in rồi xuống dòng
        System.out.printf("%-10s|%5d|%12.2f%n", name, quantity, price * quantity); // mỗi % lấy một đối số theo thứ tự
        System.out.println(1 + 2 + "3"); // hai số cộng trước, rồi mới ghép chuỗi
        System.out.println('a' + 'b'); // hai char được cộng như hai số
    }
}
