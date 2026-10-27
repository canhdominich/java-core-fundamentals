import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderScan {
    public static void main(String[] args) {
        String message = "Order DH-2026-0042 ship to 0912345678";
        // Hai cặp ngoặc tròn tạo ra nhóm 1 (năm) và nhóm 2 (số thứ tự)
        Pattern code = Pattern.compile("DH-(\\d{4})-(\\d{4})");
        // matches() đòi khuôn phủ cả văn bản nên ở đây là false
        System.out.println("matches: " + code.matcher(message).matches());
        // Matcher mới, còn đứng ở đầu văn bản
        Matcher m = code.matcher(message);
        // Chỉ đọc nhóm khi find() đã trả về true
        if (m.find()) {
            System.out.println("whole: " + m.group());
            System.out.println("year: " + m.group(1));
            System.out.println("number: " + m.group(2));
            // end() là vị trí ngay sau ký tự cuối của phần khớp
            System.out.println("span: " + m.start() + " to " + m.end());
        }
    }
}
