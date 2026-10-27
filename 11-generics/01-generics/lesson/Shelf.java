import java.util.ArrayList;
import java.util.List;

public class Shelf<T> {
    private final List<T> items = new ArrayList<>();

    void add(T item) {
        items.add(item);
    }

    T get(int index) {
        return items.get(index);
    }

    int size() {
        return items.size();
    }

    @Override
    public String toString() {
        return items.toString();
    }

    public static void main(String[] args) {
        Shelf<String> drinks = new Shelf<>();
        drinks.add("Latte");
        drinks.add("Mocha");
        Shelf<Integer> prices = new Shelf<>();
        prices.add(10);
        prices.add(25);
        prices.add(40);
        System.out.println(drinks + " size " + drinks.size());
        System.out.println(prices + " size " + prices.size());
        System.out.println("first drink: " + drinks.get(0));
    }
}
