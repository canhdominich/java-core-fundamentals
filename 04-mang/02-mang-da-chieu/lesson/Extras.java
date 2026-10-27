import java.util.Arrays;

public class Extras {
    static long sumByRows(int[][] g) {
        long s = 0;
        for (int r = 0; r < g.length; r++) {
            for (int c = 0; c < g[r].length; c++) {
                s += g[r][c];
            }
        }
        return s;
    }

    static long sumByColumns(int[][] g) {
        long s = 0;
        for (int c = 0; c < g[0].length; c++) {
            for (int r = 0; r < g.length; r++) {
                s += g[r][c];
            }
        }
        return s;
    }

    public static void main(String[] args) {
        int[][] m = new int[3][4];
        System.out.println("rows distinct: " + (m[0] != m[1]) + ", row length " + m[0].length);
        m[1] = new int[10];
        System.out.println("row 1 now has " + m[1].length + " cells, row 0 has " + m[0].length);
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{1, 2}, {3, 4}};
        System.out.println("equals " + Arrays.equals(a, b) + ", deepEquals " + Arrays.deepEquals(a, b));
        int[][] big = new int[4000][4000];
        for (int r = 0; r < big.length; r++) {
            Arrays.fill(big[r], 1);
        }
        long best1 = Long.MAX_VALUE;
        long best2 = Long.MAX_VALUE;
        long x = 0;
        for (int k = 0; k < 5; k++) {
            long t0 = System.nanoTime();
            x += sumByRows(big);
            best1 = Math.min(best1, System.nanoTime() - t0);
            long t1 = System.nanoTime();
            x += sumByColumns(big);
            best2 = Math.min(best2, System.nanoTime() - t1);
        }
        System.out.println("same total " + (x % 2 == 0));
        System.out.println("rows first " + best1 / 1_000_000 + " ms, columns first " + best2 / 1_000_000 + " ms");
    }
}
