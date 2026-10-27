import java.util.ArrayList;
import java.util.List;

public class Skip {
    public static void main(String[] args) {
        List<String> orders = new ArrayList<>(
                List.of("Latte", "CANCELLED", "CANCELLED", "Mocha", "Tea", "Latte"));
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).equals("CANCELLED")) {
                orders.remove(i);
            }
        }
        System.out.println(orders + " size " + orders.size());
    }
}
