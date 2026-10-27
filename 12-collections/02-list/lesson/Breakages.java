import java.util.ArrayList;
import java.util.List;

public class Breakages {
    public static void main(String[] args) {
        List<String> orders = new ArrayList<>(List.of("Latte", "Mocha", "Tea"));
        try {
            orders.get(3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("1: " + e);
        }

        List<Integer> codes = new ArrayList<>(List.of(10, 20, 30, 1));
        codes.remove(1);
        System.out.println("2: " + codes);

        List<String> board = new ArrayList<>(List.of("Latte", "CANCELLED", "Tea", "CANCELLED", "Mocha"));
        try {
            for (String order : board) {
                if (order.equals("CANCELLED")) {
                    board.remove(order);
                }
            }
        } catch (java.util.ConcurrentModificationException e) {
            System.out.println("3: " + e + " " + board);
        }

        try {
            List.of("Latte").add("Tea");
        } catch (UnsupportedOperationException e) {
            System.out.println("4: " + e);
        }
    }
}
