import java.util.ArrayList;
import java.util.List;

public class Check {
    static <T extends Comparable<T>> T smaller(T a, T b) {
        return a.compareTo(b) <= 0 ? a : b;
    }

    public static void main(String[] args) {
        List<Integer> a = List.of(1, 2);
        List<? extends Number> view = a;
        Number first = view.get(0);
        List<Number> sink = new ArrayList<>();
        List<? super Integer> target = sink;
        target.add(3);
        Object any = target.get(0);
        System.out.println(first + " " + sink + " " + any);
        System.out.println(smaller(3, 8) + " " + smaller("pear", "apple"));
    }
}
