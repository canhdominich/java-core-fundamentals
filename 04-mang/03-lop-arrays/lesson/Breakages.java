import java.util.Arrays;
import java.util.Collections;

public class Breakages {
    public static void main(String[] args) {
        int[] scores = {70, 95, 40};
        System.out.println("print directly starts with [I@: " + scores.toString().startsWith("[I@"));
        int[] ids = {50, 10, 30, 20, 40};
        System.out.println("binarySearch unsorted ids, find 30: " + Arrays.binarySearch(ids, 30));
        System.out.println("binarySearch unsorted ids, find 20: " + Arrays.binarySearch(ids, 20));
        int[] sortedIds = ids.clone();
        Arrays.sort(sortedIds);
        System.out.println("binarySearch sorted ids, find 30: " + Arrays.binarySearch(sortedIds, 30));
        int[] x = {1, 2, 3};
        int[] y = {1, 2, 3};
        System.out.println("== " + (x == y) + ", equals " + x.equals(y) + ", Arrays.equals " + Arrays.equals(x, y));
        int[] a = {4, 3, 2, 1, 0};
        Arrays.sort(a, 1, 4);
        System.out.println("q2: " + Arrays.toString(a));
        int[] p = {30, 10, 20};
        int[] q = p;
        Arrays.sort(q);
        int[] r = {10, 20, 30};
        System.out.println("q3: " + p[0] + " " + (p == q) + " " + Arrays.equals(p, r));
        int[] s = {10, 20, 30, 40, 50};
        System.out.println("q4: " + Arrays.binarySearch(s, 35) + " " + Arrays.binarySearch(s, 5) + " " + Arrays.binarySearch(s, 99));
        int[] c = {3, 1, 2};
        int[] d = Arrays.copyOf(c, 3);
        Arrays.sort(c);
        System.out.println("cc2: " + Arrays.toString(c) + " " + Arrays.toString(d));
        Integer[] boxed = {3, 1, 2};
        Arrays.sort(boxed, Collections.reverseOrder());
        System.out.println("descending: " + Arrays.toString(boxed));
    }
}
