import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CakeMessages {
    public static void main(String[] args) {
        String message = "Order DH-2026-0042 ship to 0912345678";
        // Mười chữ số liền nhau
        Pattern phone = Pattern.compile("\\d{10}");
        Matcher found = phone.matcher(message);
        if (found.find()) {
            System.out.println("step 1: " + found.group());
        }
        // Cùng một khuôn, hai văn bản, mỗi văn bản một Matcher
        System.out.println("step 2: " + phone.matcher("0912345678").matches() + " " + phone.matcher(message).matches());
        Matcher parts = Pattern.compile("DH-(\\d{4})-(\\d{4})").matcher(message);
        if (parts.find()) {
            System.out.println("step 3: " + parts.group(1) + " " + parts.group(2));
        }
        String two = "DH-2026-0042 and DH-2026-0043";
        List<String> codes = new ArrayList<>();
        Matcher every = Pattern.compile("DH-\\d{4}-\\d{4}").matcher(two);
        // Lặp find() để gom mọi mã đơn
        while (every.find()) {
            codes.add(every.group());
        }
        System.out.println("step 4: " + codes);
        System.out.println("step 5: " + phone.matcher(message).replaceAll("#".repeat(10)));
    }
}
