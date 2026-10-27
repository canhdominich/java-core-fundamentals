import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Greedy {
    static List<String> all(String regex, String text) {
        List<String> found = new ArrayList<>();
        Matcher m = Pattern.compile(regex).matcher(text);
        // Mỗi lần find() cho một chỗ khớp tiếp theo, dừng khi hết
        while (m.find()) {
            found.add(m.group());
        }
        return found;
    }

    public static void main(String[] args) {
        String text = "<b>Latte</b> and <i>Tea</i>";
        // Mẫu tham lam: .+ lấy nhiều nhất rồi mới lùi
        System.out.println("greedy: " + all("<.+>", text));
        // Thêm dấu hỏi để .+? dừng ở dấu lớn hơn đầu tiên
        System.out.println("lazy: " + all("<.+?>", text));
    }
}
