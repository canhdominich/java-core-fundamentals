import java.util.ArrayList;
import java.util.List;

public class PriceTools {
    static <T extends Number> double total(List<T> items) {
        double sum = 0;
        for (T item : items) {
            sum += item.doubleValue();
        }
        return sum;
    }

    static double sumAll(List<? extends Number> prices) {
        double sum = 0;
        for (Number price : prices) {
            sum += price.doubleValue();
        }
        return sum;
    }

    static void fill(List<? super Integer> target) {
        for (int i = 1; i <= 3; i++) {
            target.add(i);
        }
    }

    // Đọc từ nguồn (extends), ghi vào đích (super)
    static <T> void copyAll(List<? extends T> source, List<? super T> destination) {
        for (T item : source) {
            destination.add(item);
        }
    }

    static <T extends Comparable<T>> T biggest(List<T> items) {
        T best = items.get(0);
        for (T item : items) {
            if (item.compareTo(best) > 0) {
                best = item;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("step 1: " + total(List.of(1, 2, 3)));
        System.out.println("step 2: " + sumAll(List.of(1.5, 2.5)));
        List<Object> objects = new ArrayList<>();
        fill(objects);
        System.out.println("step 3: " + objects);
        List<Number> numbers = new ArrayList<>();
        copyAll(List.of(1, 2), numbers);
        copyAll(List.of(1.5), numbers);
        System.out.println("step 4: " + numbers);
        System.out.println("step 5: " + biggest(List.of(7, 42, 3)) + " " + biggest(List.of("pear", "apple")));
    }
}
