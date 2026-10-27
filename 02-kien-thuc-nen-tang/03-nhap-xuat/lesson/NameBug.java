import java.util.Scanner;

public class NameBug {
    public static void main(String[] args) {
        Scanner in = new Scanner("25\nAn Binh\n"); // giả lập bàn phím: gõ 25, Enter, An Binh, Enter
        int quantity = in.nextInt(); // chỉ lấy token 25, dấu xuống dòng vẫn nằm lại
        String name = in.nextLine(); // gặp ngay dấu xuống dòng nên trả về chuỗi rỗng
        System.out.println("naive: quantity=" + quantity + ", name=[" + name + "]");
        Scanner in2 = new Scanner("25\nAn Binh\n");
        int fixedQuantity = Integer.parseInt(in2.nextLine()); // đọc cả dòng rồi mới đổi sang số
        String fixedName = in2.nextLine(); // dòng kế tiếp còn nguyên nên đọc đúng tên
        System.out.println("fixed: quantity=" + fixedQuantity + ", name=[" + fixedName + "]");
    }
}
