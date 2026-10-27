import java.util.ArrayList;
import java.util.List;

public class OrderBoard {
    private final List<String> orders = new ArrayList<>();

    public void add(String drink) {
        orders.add(drink);
    }

    public void addUrgent(String drink) {
        orders.add(0, drink);
    }

    public boolean cancel(String drink) {
        return orders.remove(drink);
    }

    public int cancelAll(String drink) {
        int before = orders.size();
        orders.removeIf(order -> order.equals(drink));
        return before - orders.size();
    }

    public String next() {
        if (orders.isEmpty()) {
            return null;
        }
        return orders.remove(0);
    }

    public int size() {
        return orders.size();
    }

    @Override
    public String toString() {
        return orders.toString();
    }

    public static void main(String[] args) {
        OrderBoard board = new OrderBoard();
        board.add("Latte");
        board.add("Mocha");
        board.add("Latte");
        board.addUrgent("Espresso");
        System.out.println(board + " size " + board.size());
        System.out.println("cancel Tea: " + board.cancel("Tea"));
        System.out.println("cancelAll Latte: " + board.cancelAll("Latte") + " " + board);
        System.out.println("next: " + board.next() + " " + board);
        System.out.println("next: " + board.next() + " " + board);
        System.out.println("next: " + board.next() + " " + board);
    }
}
