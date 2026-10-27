import java.util.ArrayList;
import java.util.List;

public class Selfcheck {
    public static void main(String[] args) {
        final List<String> hosts = new ArrayList<>(List.of("a"));
        hosts.add("b");
        System.out.println(hosts);

        Counter c1 = new Counter();
        new Counter();
        Counter c3 = new Counter();
        System.out.println(c1.id + " " + c3.id + " " + Counter.count);

        final int[] arr = {1, 2};
        arr[0] = 9;
        System.out.println(arr[0]);
    }
}

class Counter {
    static int count;
    int id;

    Counter() {
        id = ++count;
    }
}
