import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KitchenOverview {
    public static void main(String[] args) {
        // Biến kiểu List trỏ tới một ArrayList: ba đơn, Latte xuất hiện hai lần
        List<String> orders = new ArrayList<>();
        orders.add("Latte");
        orders.add("Mocha");
        orders.add("Latte");
        System.out.println("list: " + orders + " size " + orders.size());

        // Chép sang Set: Latte trùng bị bỏ nên chỉ còn 2 phần tử
        Set<String> menu = new HashSet<>(orders);
        System.out.println("set size " + menu.size() + ", has Mocha " + menu.contains("Mocha"));

        // Map lưu cặp khoá và giá trị: số khách và đồ họ gọi
        Map<Integer, String> byCustomer = new HashMap<>();
        byCustomer.put(7, "Latte");
        byCustomer.put(8, "Mocha");
        // Khoá 9 không có nên get trả về null, không báo lỗi
        System.out.println("customer 7: " + byCustomer.get(7) + ", customer 9: " + byCustomer.get(9));
    }
}
