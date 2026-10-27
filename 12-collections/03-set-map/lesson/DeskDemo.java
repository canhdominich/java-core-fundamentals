import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DeskDemo {
    public static void main(String[] args) {
        // Set bỏ trùng: add trả về true nếu mã mới, false nếu mã đã có
        Set<String> seen = new HashSet<>();
        System.out.println("add: " + seen.add("A100") + " " + seen.add("A101") + " " + seen.add("A100") + ", size " + seen.size());

        // Map: put trả về giá trị cũ của khoá (null nếu khoá mới)
        Map<Integer, String> byCustomer = new HashMap<>();
        System.out.println("put: " + byCustomer.put(7, "Latte") + " " + byCustomer.put(8, "Mocha") + " " + byCustomer.put(7, "Tea"));
        // get thiếu khoá cho null, getOrDefault cho giá trị dự phòng
        System.out.println("get: " + byCustomer.get(7) + " " + byCustomer.get(9) + " " + byCustomer.getOrDefault(9, "none"));
        System.out.println("has 8: " + byCustomer.containsKey(8) + ", size " + byCustomer.size());
    }
}
