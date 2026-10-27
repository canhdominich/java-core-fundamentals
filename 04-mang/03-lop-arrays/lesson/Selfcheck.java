import java.util.Arrays;

public class Selfcheck {
    static String where(int[] sorted, int key) {
        int i = Arrays.binarySearch(sorted, key);
        if (i >= 0) {
            return "found at " + i;
        }
        return "insert at " + (-i - 1);
    }

    static int[] insertSorted(int[] sorted, int value) {
        int i = Arrays.binarySearch(sorted, value);
        int at = i >= 0 ? i : -i - 1;
        int[] result = new int[sorted.length + 1];
        System.arraycopy(sorted, 0, result, 0, at);
        result[at] = value;
        System.arraycopy(sorted, at, result, at + 1, sorted.length - at);
        return result;
    }

    public static void main(String[] args) {
        int[] a = {5, 2, 8, 1};
        int[] b = a.clone();
        Arrays.sort(a);
        Arrays.fill(b, 3, 4, 0);
        boolean same = Arrays.equals(a, b);
        System.out.println("b1 a " + Arrays.toString(a) + " b " + Arrays.toString(b) + " same " + same);
        int[] ids = {10, 20, 30, 40, 50};
        System.out.println("b2 " + where(ids, 30) + ", " + where(ids, 35) + ", " + where(ids, 5) + ", " + where(ids, 99));
        int[] x = {1, 2, 3};
        int[] y = {1, 2, 3};
        System.out.println("b3 " + (x == y) + " " + x.equals(y) + " " + Arrays.equals(x, y));
        int[] scores = {70, 95, 40, 85, 60, 95, 88};
        int[] ranked = scores.clone();
        Arrays.sort(ranked);
        System.out.println("b4 original " + Arrays.toString(scores));
        System.out.println("b4 top 3: " + ranked[ranked.length - 1] + " " + ranked[ranked.length - 2] + " " + ranked[ranked.length - 3]);
        System.out.println("b4 median " + ranked[ranked.length / 2]);
        System.out.println("b5 " + Arrays.toString(insertSorted(new int[]{10, 20, 30, 50}, 40)) + " " + Arrays.toString(insertSorted(new int[]{10, 20, 30, 50}, 5)) + " " + Arrays.toString(insertSorted(new int[]{10, 20, 30, 50}, 20)));
    }
}
