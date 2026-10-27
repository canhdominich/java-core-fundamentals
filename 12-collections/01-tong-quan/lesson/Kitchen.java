import java.util.*;

public class Kitchen {
    public static void main(String[] args) {
        List<String> queue = new ArrayList<>();
        queue.add("Latte");
        queue.add("Mocha");
        queue.add("Latte");
        System.out.println("step 1: " + queue);

        Set<String> menu = new HashSet<>();
        menu.addAll(queue);  // Latte trùng nên menu chỉ có 2 phần tử
        System.out.println("step 2: " + menu.size());

        Map<Integer, String> byCustomer = new HashMap<>();
        byCustomer.put(7, "Latte");
        byCustomer.put(8, "Mocha");
        byCustomer.put(9, "Latte");  // giá trị trùng thì được, khoá trùng thì không
        System.out.println("step 3: " + byCustomer.get(8) + " " + byCustomer.get(10));

        Set<String> seen = new HashSet<>();
        boolean first = false;
        boolean again = false;
        first = seen.add("A100");  // lần đầu: true
        again = seen.add("A100");  // đã có: false, tập không đổi
        System.out.println("step 4: " + first + " " + again);

        // Bản sao chỉ đọc chụp 3 đơn hiện có
        List<String> board = List.copyOf(queue);
        queue.add("Tea");  // queue thành 4 đơn, board vẫn 3
        System.out.println("step 5: " + queue.size() + " " + board.size());
    }
}
