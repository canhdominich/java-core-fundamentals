import java.util.Locale;

public class Deeper {
    public static void main(String[] args) {
        double value = 1234567.89;
        System.out.println("US      " + String.format(Locale.US, "%,.2f", value)); // dấu phẩy ngăn nghìn
        System.out.println("GERMANY " + String.format(Locale.GERMANY, "%,.2f", value)); // ngược lại với Locale.US
        System.out.println("ROOT    " + String.format(Locale.ROOT, "%.2f", value)); // kết quả cố định, không phụ thuộc máy
        System.out.println('a' + 'b'); // 97 + 98
        System.err.println("this line goes to stderr"); // luồng riêng cho cảnh báo
        System.out.println("this line goes to stdout");
    }
}
