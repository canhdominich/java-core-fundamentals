import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Bench {
    static final int N = 50000;

    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>();
        LinkedList<Integer> linked = new LinkedList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            array.add(i);
            linked.add(i);
            deque.add(i);
        }

        long t0 = System.nanoTime();
        while (!array.isEmpty()) {
            array.remove(0);
        }
        long t1 = System.nanoTime();
        while (!linked.isEmpty()) {
            linked.removeFirst();
        }
        long t2 = System.nanoTime();
        while (!deque.isEmpty()) {
            deque.pollFirst();
        }
        long t3 = System.nanoTime();

        System.out.println("ArrayList.remove(0): " + (t1 - t0) / 1_000_000 + " ms");
        System.out.println("LinkedList.removeFirst: " + (t2 - t1) / 1_000_000 + " ms");
        System.out.println("ArrayDeque.pollFirst: " + (t3 - t2) / 1_000_000 + " ms");
    }
}
