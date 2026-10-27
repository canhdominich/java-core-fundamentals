import java.util.ArrayList;
import java.util.List;

public class CafeOrders {
    public static void main(String[] args) {
        // Biến kiểu List (hợp đồng) trỏ tới một ArrayList (cách làm) đang rỗng
        List<String> orders = new ArrayList<>();
        orders.add("Latte");  // vào ghế 0
        orders.add("Mocha");  // vào ghế 1
        orders.add("Tea");    // vào ghế 2
        System.out.println(orders);

        // Chèn vào ghế 1: Mocha và Tea phải dồn sang phải
        orders.add(1, "Espresso");
        System.out.println(orders);

        // remove(2) lấy phần tử ở ghế 2 ra và trả về nó, các ghế sau dồn sang trái
        String removed = orders.remove(2);
        System.out.println(removed + " " + orders);

        // get chỉ đọc nên không ghế nào dịch chuyển
        System.out.println(orders.get(0) + " " + orders.size());
    }
}
