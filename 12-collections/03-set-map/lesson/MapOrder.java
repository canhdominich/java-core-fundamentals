import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapOrder {
    public static void main(String[] args) {
        // Ba Map nhận cùng dữ liệu, chỉ khác thứ tự duyệt
        Map<String, Integer> linked = new LinkedHashMap<>();
        Map<String, Integer> sorted = new TreeMap<>();
        Map<String, Integer> hashed = new HashMap<>();
        for (String drink : new String[] {"Tea", "Latte", "Mocha"}) {
            linked.put(drink, drink.length());
            sorted.put(drink, drink.length());
            hashed.put(drink, drink.length());
        }
        System.out.println("LinkedHashMap " + linked);
        System.out.println("TreeMap       " + sorted);
        // Không in thứ tự của HashMap vì không được bảo đảm
        System.out.println("HashMap size  " + hashed.size());
    }
}
