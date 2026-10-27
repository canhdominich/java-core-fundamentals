import java.util.ArrayList;
import java.util.List;

public class RecentOrders {
    private final List<String> orders = new ArrayList<>();
    private final int limit;

    RecentOrders(int limit) {
        this.limit = limit;
    }

    void add(String order) {
        orders.add(order);
        // Bỏ đơn cũ nhất để danh sách không lớn mãi
        if (orders.size() > limit) {
            orders.remove(0);
        }
    }

    int size() {
        return orders.size();
    }

    @Override
    public String toString() {
        return orders.toString();
    }

    public static void main(String[] args) {
        RecentOrders recent = new RecentOrders(3);
        for (String order : new String[] {"Latte", "Mocha", "Tea", "Mint", "Cocoa"}) {
            recent.add(order);
            System.out.println(recent + " size " + recent.size());
        }
        RecentOrders many = new RecentOrders(1000);
        for (int i = 0; i < 5000; i++) {
            many.add("order " + i);
        }
        System.out.println(many.size());
    }
}
