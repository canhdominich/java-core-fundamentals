import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Extras {
    public static void main(String[] args) {
        int[] dup = {10, 20, 20, 20, 30};
        System.out.println("duplicates, find 20: " + Arrays.binarySearch(dup, 20));
        int[] primitive = {1, 2, 3};
        Integer[] boxed = {1, 2, 3};
        System.out.println("asList size from int[] " + Arrays.asList(primitive).size() + ", from Integer[] " + Arrays.asList(boxed).size());
        String[] names = {"An", "Binh", "Chi"};
        List<String> view = Arrays.asList(names);
        names[0] = "Changed";
        System.out.println("asList sees the array change: " + view);
        try {
            view.add("Dung");
        } catch (UnsupportedOperationException e) {
            System.out.println("add on asList: UnsupportedOperationException");
        }
        int n = 2_000_000;
        Random random = new Random(42);
        int[] prim = new int[n];
        Integer[] box = new Integer[n];
        for (int i = 0; i < n; i++) {
            prim[i] = random.nextInt();
            box[i] = prim[i];
        }
        long bestP = Long.MAX_VALUE;
        long bestB = Long.MAX_VALUE;
        for (int k = 0; k < 3; k++) {
            int[] p = prim.clone();
            Integer[] b = box.clone();
            long t0 = System.nanoTime();
            Arrays.sort(p);
            bestP = Math.min(bestP, System.nanoTime() - t0);
            long t1 = System.nanoTime();
            Arrays.sort(b);
            bestB = Math.min(bestB, System.nanoTime() - t1);
        }
        System.out.println("int[] " + bestP / 1_000_000 + " ms, Integer[] " + bestB / 1_000_000 + " ms");
        int[] big = prim.clone();
        Arrays.sort(big);
        long t2 = System.nanoTime();
        int hits = 0;
        for (int i = 0; i < 1000; i++) {
            if (Arrays.binarySearch(big, prim[i]) >= 0) {
                hits++;
            }
        }
        long searchMs = (System.nanoTime() - t2) / 1_000_000;
        System.out.println("1000 binarySearch hits " + hits + " in " + searchMs + " ms");
    }
}
