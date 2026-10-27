import java.util.Scanner;

public class BadInput {
    public static void main(String[] args) {
        Scanner in = new Scanner("abc 7");
        System.out.println("first check: " + in.hasNextInt()); // nhìn trước, không lấy gì ra
        String bad = in.next(); // đọc bỏ token sai
        System.out.println("thrown away: " + bad);
        System.out.println("second check: " + in.hasNextInt());
        int n = in.nextInt(); // bây giờ token đầu là 7 nên đọc được
        System.out.println("n = " + n);
    }
}
