import java.util.ArrayList;
import java.util.List;

public class PriceBoard {
    static <T> void copyAll(List<? extends T> source, List<? super T> destination) {
        for (T item : source) {
            destination.add(item);
        }
    }

    static double average(List<? extends Number> prices) {
        double sum = 0;
        for (Number price : prices) {
            sum += price.doubleValue();
        }
        return sum / prices.size();
    }

    public static void main(String[] args) {
        List<Integer> whole = List.of(1, 2, 3);
        List<Double> cents = List.of(1.5, 2.5);
        List<Number> all = new ArrayList<>();
        copyAll(whole, all);
        copyAll(cents, all);
        System.out.println(all + " avg " + average(all));
    }
}
