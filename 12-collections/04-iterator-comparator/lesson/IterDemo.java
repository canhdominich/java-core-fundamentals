import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IterDemo {
    public static void main(String[] args) {
        List<String> orders = new ArrayList<>(List.of("Latte", "CANCELLED", "CANCELLED", "Mocha", "Tea", "Latte"));
        // Iterator giữ một con trỏ, đứng trước ghế 0 lúc bắt đầu
        Iterator<String> it = orders.iterator();
        while (it.hasNext()) {
            String order = it.next();  // trả về phần tử kế tiếp và đẩy con trỏ đi
            if (order.equals("CANCELLED")) {
                it.remove();  // xoá đúng phần tử next vừa trả về, con trỏ lùi lại một bước
            }
        }
        System.out.println(orders + " size " + orders.size());
    }
}
