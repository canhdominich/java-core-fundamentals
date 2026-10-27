import java.util.ArrayList;
import java.util.List;

public class KitchenBoard {
    public static void main(String[] args) {
        List<String> orders = new ArrayList<>();
        orders.add("Latte");
        orders.add("Mocha");
        orders.add("Tea");
        System.out.println("step 1: " + orders);

        orders.add(0, "Espresso");
        System.out.println("step 2: " + orders);

        boolean cancelled = orders.remove("Mocha");
        String served = orders.remove(0);
        System.out.println("step 3: " + cancelled + " " + served + " " + orders);

        orders.add("Latte");
        int lattes = 0;
        for (String order : orders) {
            if (order.equals("Latte")) {
                lattes++;
            }
        }
        System.out.println("step 4: " + lattes + " " + orders);

        orders.removeIf(order -> order.equals("Latte"));
        System.out.println("step 5: " + orders);
    }
}
