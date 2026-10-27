import java.util.ArrayList;
import java.util.List;

public class PriceSum {
    // ? extends Number: chỉ đọc, mỗi phần tử ra là một Number
    static double sumAll(List<? extends Number> prices) {
        double total = 0;
        for (Number price : prices) {
            total += price.doubleValue();
        }
        return total;
    }

    // ? super Integer: chỉ ghi, thêm được Integer vào danh sách Integer, Number hoặc Object
    static void addDefaults(List<? super Integer> target) {
        target.add(10);
        target.add(20);
    }

    public static void main(String[] args) {
        List<Integer> whole = List.of(1, 2, 3);
        List<Double> cents = List.of(1.5, 2.5);
        System.out.println(sumAll(whole) + " " + sumAll(cents));
        List<Number> mixed = new ArrayList<>();
        addDefaults(mixed);
        System.out.println(mixed);
    }
}
