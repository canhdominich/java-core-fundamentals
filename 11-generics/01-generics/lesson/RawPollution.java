import java.util.ArrayList;
import java.util.List;

public class RawPollution {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        // Kiểu thô: tắt nhãn, trình biên dịch chỉ cảnh báo
        List raw = names;
        raw.add(42);
        System.out.println("added without error, size " + names.size());
        try {
            // Lỗi chỉ nổ ở đây, xa dòng gây ra nó
            String first = names.get(0);
            System.out.println(first);
        } catch (ClassCastException e) {
            System.out.println("read failed: " + e.getClass().getSimpleName());
        }
    }
}
