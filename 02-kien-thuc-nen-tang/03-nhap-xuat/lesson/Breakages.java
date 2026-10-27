import java.util.Scanner;

public class Breakages {
    public static void main(String[] args) {
        Scanner in = new Scanner("25\nAn Binh\n");
        int quantity = in.nextInt();
        System.out.println("1: quantity=" + quantity + ", name=[" + in.nextLine() + "]"); // tên trống, không báo lỗi
        Scanner bad = new Scanner("abc");
        try {
            bad.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("2: " + e + ", token still there: [" + bad.next() + "]");
        }
        try {
            System.out.printf("%d%n", 3.5); // %d không nhận số thực
        } catch (java.util.IllegalFormatConversionException e) {
            System.out.println("3: " + e);
        }
        Scanner empty = new Scanner("");
        try {
            empty.nextLine();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("4: " + e);
        }
    }
}
