import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderParser {
    // Biên dịch một lần ở mức class, dùng lại cho mọi dòng
    private static final Pattern LINE = Pattern.compile("DH-(\\d{4})-(\\d{4}) (\\d{10}) (\\d+)x (\\w+)");

    record Order(String code, String phone, int quantity, String drink) {
    }

    static Order parse(String line) {
        Matcher m = LINE.matcher(line);
        // Dòng sai định dạng thì trả null, không ném lỗi
        if (!m.matches()) {
            return null;
        }
        return new Order("DH-" + m.group(1) + "-" + m.group(2), m.group(3), Integer.parseInt(m.group(4)), m.group(5));
    }

    public static void main(String[] args) {
        String[] lines = {"DH-2026-0042 0912345678 3x Latte", "DH-2026-0043 0987654321 1x Tea", "DH-26-1 12345 x Mocha"};
        for (String line : lines) {
            System.out.println(parse(line));
        }
    }
}
