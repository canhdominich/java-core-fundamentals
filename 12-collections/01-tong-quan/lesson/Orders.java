import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;

public class Orders {
    public static void main(String[] args) {
        List<String> orders = new ArrayList<>(List.of("Tea", "Latte", "Tea", "Mocha", "Latte"));
        System.out.println("list          " + orders);
        // Hai tập dưới đây có thứ tự được bảo đảm, HashSet thì không
        System.out.println("LinkedHashSet " + new LinkedHashSet<>(orders));
        System.out.println("TreeSet       " + new TreeSet<>(orders));
        // Chỉ in size của HashSet vì thứ tự duyệt của nó không được hứa
        System.out.println("HashSet size  " + new HashSet<>(orders).size());
    }
}
