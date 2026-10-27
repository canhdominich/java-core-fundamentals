import java.util.List;

public class BoundsDemo {
    // T extends Number: trình biên dịch biết T có doubleValue()
    static <T extends Number> double total(List<T> items) {
        double sum = 0;
        for (T item : items) {
            sum += item.doubleValue();
        }
        return sum;
    }

    // T extends Comparable<T>: trình biên dịch biết T có compareTo()
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
        System.out.println(total(List.of(1, 2, 3)) + " " + total(List.of(1.5, 2.5)));
        System.out.println(biggest(List.of(7, 42, 3)) + " " + biggest(List.of("pear", "apple")));
    }
}
