import java.util.Arrays;

public class Selfcheck {
    static int maxIndex(int[] a) {
        int best = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[best]) {
                best = i;
            }
        }
        return best;
    }

    static void reverse(int[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    static String report(int[] s) {
        int total = 0;
        int high = s[0];
        int low = s[0];
        for (int x : s) {
            total += x;
            if (x > high) {
                high = x;
            }
            if (x < low) {
                low = x;
            }
        }
        double avg = (double) total / s.length;
        int above = 0;
        for (int x : s) {
            if (x > avg) {
                above++;
            }
        }
        return "average " + avg + ", highest " + high + ", lowest " + low + ", above average " + above;
    }

    static int[] histogram(int[] s) {
        int[] counts = new int[11];
        for (int x : s) {
            counts[x]++;
        }
        return counts;
    }

    public static void main(String[] args) {
        int[] a = {4, 1, 7};
        int[] b = a;
        b[1] = 5;
        int[] c = Arrays.copyOf(a, 4);
        c[3] = a[1] + b[2];
        System.out.println("b1 " + Arrays.toString(a) + " " + Arrays.toString(b) + " " + Arrays.toString(c));
        System.out.println("b2 " + maxIndex(new int[]{3, 9, 2, 9}));
        int[] r = {1, 2, 3, 4, 5};
        reverse(r);
        System.out.println("b3 " + Arrays.toString(r));
        System.out.println("b4 " + report(new int[]{7, 9, 5, 10, 8, 6}));
        System.out.println("b5 " + Arrays.toString(histogram(new int[]{8, 6, 9, 8, 10, 7, 8, 6, 9, 10, 8, 5})));
        System.out.println("q2 " + maxIndex(new int[]{3, 8, 2, 8, 5}));
        int[] p = {1, 2, 3};
        int[] q = p;
        int[] rr = Arrays.copyOf(p, 3);
        q[0] = 10;
        rr[1] = 20;
        p[2] = 30;
        System.out.println("q3 " + Arrays.toString(p) + " " + Arrays.toString(q) + " " + Arrays.toString(rr));
        int[] x = {5, 6};
        int[] y = x;
        y = new int[]{7, 8};
        System.out.println("cc3 " + x[0] + " " + y[0]);
        int[] d = {1, 2, 3};
        for (int v : d) {
            v = v * 2;
        }
        System.out.println("cc2 " + Arrays.toString(d));
        int[] arr = {1, 2, 3, 4, 5};
        try {
            for (int i = 0; i <= arr.length; i++) {
                total(arr, i);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("q4 " + e.getMessage());
        }
    }

    static int total(int[] a, int i) {
        return a[i];
    }
}
