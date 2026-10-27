import java.util.ArrayList;
import java.util.List;

public class Deep {
    record Range(int min, int max) {
    }

    static final List<String> trace = new ArrayList<>();

    static Range rangeOf(int[] data) {
        int min = data[0];
        int max = data[0];
        for (int value : data) {
            min = Math.min(min, value);
            max = Math.max(max, value);
        }
        return new Range(min, max);
    }

    static double average(int[] data) {
        int total = 0;
        for (int value : data) {
            total += value;
        }
        return (double) total / data.length; // chia số thực cho 0 cho NaN, không ném lỗi
    }

    static int mark(String name, int value) {
        trace.add(name);
        return value;
    }

    static int sum3(int a, int b, int c) {
        trace.add("sum3");
        return a + b + c;
    }

    public static void main(String[] args) {
        System.out.println("rangeOf = " + rangeOf(new int[] {4, 9, 1, 7}));
        sum3(mark("first", 1), mark("second", 2), mark("third", 3)); // các đối số được tính từ trái sang phải trước thân phương thức
        System.out.println("order = " + trace);
        System.out.println("average of empty = " + average(new int[0]));
    }
}
