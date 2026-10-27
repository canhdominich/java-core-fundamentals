import java.util.Arrays;

public class Breakages {
    public static void main(String[] args) {
        int[][] grades = {{8, 7, 9, 6}, {5, 9, 7, 8}, {10, 6, 8, 9}};
        try {
            System.out.println(grades[3][0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("row: " + e.getMessage());
        }
        try {
            System.out.println(grades[0][4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("column: " + e.getMessage());
        }
        int[][] unallocated = new int[2][];
        try {
            unallocated[0][0] = 1;
        } catch (NullPointerException e) {
            System.out.println("null row: " + e.getMessage());
        }
        int[][] table = new int[3][];
        int[] row = new int[3];
        for (int i = 0; i < table.length; i++) {
            table[i] = row;
        }
        table[0][0] = 10;
        System.out.println("shared: " + Arrays.deepToString(table) + " " + (table[0] == table[2]));
        int[][] right = new int[3][3];
        right[0][0] = 1;
        System.out.println("right: " + Arrays.deepToString(right));
        System.out.println("toString starts with [[I@: " + Arrays.toString(grades).startsWith("[[I@"));
        int[][] shallow = grades.clone();
        shallow[0][0] = 99;
        System.out.println("shallow: grades[0][0] " + grades[0][0] + ", same row " + (shallow[0] == grades[0]));
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = a.clone();
        b[0][0] = 9;
        b[1] = new int[]{7, 7};
        System.out.println("q3: a " + Arrays.deepToString(a) + " b " + Arrays.deepToString(b));
        int[][] aa = {{1, 2}, {3, 4}};
        int[] r0 = aa[0];
        r0[1] = 9;
        System.out.println("cc2: " + aa[0][1]);
        int[][] jag = {{1}, {2, 3}, {4, 5, 6}};
        int s = 0;
        for (int r = 0; r < jag.length; r++) {
            for (int c = 0; c < jag[0].length; c++) {
                s += jag[r][c];
            }
        }
        System.out.println("cc3: " + s);
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        int t = 0;
        for (int c = 0; c < m[0].length; c++) {
            t += m[1][c] - m[0][c];
        }
        System.out.println("q2: " + t);
        try {
            int[][] tt = new int[2][];
            tt[0][0] = 1;
        } catch (NullPointerException e) {
            System.out.println("q4: " + e.getMessage());
        }
    }
}
