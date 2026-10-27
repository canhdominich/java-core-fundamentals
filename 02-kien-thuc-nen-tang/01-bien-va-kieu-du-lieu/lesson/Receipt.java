public class Receipt {
    public static void main(String[] args) {
        String item = "Mini Fan";
        int quantity = 3;
        int price = 1_500_000; // dấu gạch dưới chỉ giúp mắt đọc, máy bỏ qua
        int total = quantity * price; // total giữ một con số, không giữ công thức
        System.out.println(item + " x " + quantity + " = " + total);
        quantity = 4; // gán lại vào cùng một hộp, không viết lại kiểu
        System.out.println("Total now: " + total); // total vẫn là con số cũ
        total = quantity * price; // phải tính lại thì total mới đổi
        System.out.println(item + " x " + quantity + " = " + total);
    }
}
