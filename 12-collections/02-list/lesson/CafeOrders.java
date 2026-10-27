import java.util.ArrayList;
import java.util.List;

public class CafeOrders {
    public static void main(String[] args) {
        List<String> orders = new ArrayList<>();
        orders.add("Latte");
        orders.add("Mocha");
        orders.add("Tea");
        System.out.println(orders);

        orders.add(1, "Espresso");
        System.out.println(orders);

        String removed = orders.remove(2);
        System.out.println(removed + " " + orders);

        System.out.println(orders.get(0) + " " + orders.size());
    }
}
