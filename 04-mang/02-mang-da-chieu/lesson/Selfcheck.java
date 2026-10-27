import java.util.Arrays;

public class Selfcheck {
    static int diagonal(int[][] m) {
        int sum = 0;
        for (int i = 0; i < m.length; i++) {
            sum += m[i][i];
        }
        return sum;
    }

    static String find(int[][] g, int value) {
        for (int r = 0; r < g.length; r++) {
            for (int c = 0; c < g[r].length; c++) {
                if (g[r][c] == value) {
                    return "row " + r + " column " + c;
                }
            }
        }
        return "not found";
    }

    static int[][] pascal(int n) {
        int[][] t = new int[n][];
        for (int r = 0; r < n; r++) {
            t[r] = new int[r + 1];
            t[r][0] = 1;
            t[r][r] = 1;
            for (int c = 1; c < r; c++) {
                t[r][c] = t[r - 1][c - 1] + t[r - 1][c];
            }
        }
        return t;
    }

    public static void main(String[] args) {
        int[][] g = new int[2][3];
        g[0][1] = 5;
        int[] r = g[0];
        r[2] = 7;
        g[1] = r;
        System.out.println("b1 " + Arrays.deepToString(g) + " " + (g[0] == g[1]));
        System.out.println("b2 " + diagonal(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        System.out.println("b3 " + find(new int[][]{{8, 7, 9, 6}, {5, 9, 7, 8}, {10, 6, 8, 9}}, 9));
        int[][] d = {{7, 8, 9}, {6, 5, 7}, {9, 9, 10}, {8, 6, 7}};
        int[] st = new int[d.length];
        int[] su = new int[d[0].length];
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                st[i] += d[i][j];
                su[j] += d[i][j];
            }
        }
        int top = 0;
        for (int i = 1; i < st.length; i++) {
            if (st[i] > st[top]) {
                top = i;
            }
        }
        System.out.println("b4 student totals " + Arrays.toString(st) + " subject totals " + Arrays.toString(su) + " top " + top);
        System.out.println("b5 " + Arrays.deepToString(pascal(5)));
    }
}
