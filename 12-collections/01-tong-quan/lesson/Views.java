import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Views {
    public static void main(String[] args) {
        List<String> source = new ArrayList<>(List.of("Latte", "Mocha"));
        // Khung nhìn chỉ bọc source, không chép dữ liệu
        List<String> view = Collections.unmodifiableList(source);
        // Bản sao chụp dữ liệu lúc này, từ đây độc lập với source
        List<String> copy = List.copyOf(source);
        source.add("Tea");  // chỉ danh sách gốc và khung nhìn thấy Tea
        System.out.println("source " + source + ", view " + view + ", copy " + copy);
        try {
            view.add("Chai");
        } catch (UnsupportedOperationException e) {
            System.out.println("view.add: UnsupportedOperationException");
        }
        try {
            List.of("Latte").add("Tea");
        } catch (UnsupportedOperationException e) {
            System.out.println("List.of add: UnsupportedOperationException");
        }
    }
}
