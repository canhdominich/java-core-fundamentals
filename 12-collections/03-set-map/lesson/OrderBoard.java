import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderBoard {
    private final List<String> orders = new ArrayList<>();
    private final Set<String> seenIds = new HashSet<>();
    private final Map<Integer, String> byCustomer = new HashMap<>();

    public boolean place(String id, int customer, String drink) {
        // add trả về false nghĩa là mã đã có, dừng ngay
        if (!seenIds.add(id)) {
            return false;
        }
        orders.add(drink);
        byCustomer.put(customer, drink);  // đơn mới nhất của khách ghi đè đơn cũ
        return true;
    }

    public String drinkOf(int customer) {
        return byCustomer.getOrDefault(customer, "none");  // khách chưa có đơn thì none
    }

    public int size() {
        return orders.size();
    }

    public static void main(String[] args) {
        OrderBoard board = new OrderBoard();
        System.out.println(board.place("A100", 7, "Latte"));
        System.out.println(board.place("A101", 8, "Mocha"));
        System.out.println(board.place("A100", 9, "Tea"));
        System.out.println(board.drinkOf(7) + " " + board.drinkOf(9) + " " + board.size());
    }
}
