import java.util.ArrayList;
import java.util.List;

public class MoveToFront {
    public static void main(String[] args) {
        List<String> orders = new ArrayList<>(List.of("Latte", "Espresso", "Tea"));
        String tea = orders.remove(2);
        orders.add(0, tea);
        System.out.println(orders);
    }
}
