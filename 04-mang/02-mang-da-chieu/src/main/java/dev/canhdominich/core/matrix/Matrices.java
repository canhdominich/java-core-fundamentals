package dev.canhdominich.core.matrix;

import java.util.Arrays;

public class Matrices {

    public static String shape(int[][] matrix) {
        return matrix.length + " rows, first row has " + matrix[0].length + " columns";
    }

    public static int[][] add(int[][] a, int[][] b) {
        requireSameShape(a, b);
        int[][] result = new int[a.length][a[0].length];
        for (int r = 0; r < a.length; r++) {
            for (int c = 0; c < a[r].length; c++) {
                result[r][c] = a[r][c] + b[r][c];
            }
        }
        return result;
    }

    private static void requireSameShape(int[][] a, int[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalArgumentException("shapes differ: " + a.length + "x" + a[0].length + " and " + b.length + "x" + b[0].length);
        }
    }

    public static int[][] transpose(int[][] matrix) {
        int[][] result = new int[matrix[0].length][matrix.length];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                result[c][r] = matrix[r][c];
            }
        }
        return result;
    }

    public static int[][] multiply(int[][] a, int[][] b) {
        if (a[0].length != b.length) {
            throw new IllegalArgumentException("columns of the left matrix (" + a[0].length + ") must equal rows of the right matrix (" + b.length + ")");
        }
        int[][] result = new int[a.length][b[0].length];
        for (int r = 0; r < a.length; r++) {
            for (int c = 0; c < b[0].length; c++) {
                for (int k = 0; k < b.length; k++) {
                    result[r][c] += a[r][k] * b[k][c];
                }
            }
        }
        return result;
    }

    public static int[] rowSums(int[][] matrix) {
        int[] sums = new int[matrix.length];
        for (int r = 0; r < matrix.length; r++) {
            for (int value : matrix[r]) {
                sums[r] += value;
            }
        }
        return sums;
    }

    public static int[][] pascal(int rows) {
        int[][] triangle = new int[rows][];
        for (int r = 0; r < rows; r++) {
            triangle[r] = new int[r + 1];
            triangle[r][0] = 1;
            triangle[r][r] = 1;
            for (int c = 1; c < r; c++) {
                triangle[r][c] = triangle[r - 1][c - 1] + triangle[r - 1][c];
            }
        }
        return triangle;
    }

    public static int neighbours(int[][] grid, int row, int col) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) {
                    continue;
                }
                int r = row + dr;
                int c = col + dc;
                if (r >= 0 && r < grid.length && c >= 0 && c < grid[r].length && grid[r][c] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public static String sharedRowBug() {
        int[] row = new int[3];
        int[][] grid = new int[3][];
        Arrays.fill(grid, row);
        grid[0][0] = 1;
        return Arrays.deepToString(grid) + ", all rows are one object: " + (grid[0] == grid[2]);
    }

    public static String correctRows() {
        int[][] grid = new int[3][3];
        grid[0][0] = 1;
        return Arrays.deepToString(grid);
    }

    public static String shallowClone() {
        int[][] original = {{1, 2}, {3, 4}};
        int[][] copy = original.clone();
        copy[0][0] = 99;
        return "original " + Arrays.deepToString(original) + ", same row object: " + (original[0] == copy[0]);
    }

    public static String deepClone() {
        int[][] original = {{1, 2}, {3, 4}};
        int[][] copy = new int[original.length][];
        for (int r = 0; r < original.length; r++) {
            copy[r] = original[r].clone();
        }
        copy[0][0] = 99;
        return "original " + Arrays.deepToString(original) + ", copy " + Arrays.deepToString(copy);
    }

    public static String unallocatedRow() {
        int[][] jagged = new int[2][];
        try {
            return "value " + jagged[1][0];
        } catch (NullPointerException e) {
            return "NullPointerException: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};
        int[][] b = {{10, 20, 30}, {40, 50, 60}};
        int[][] c = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println("shape                = " + shape(a));
        System.out.println("add                  = " + Arrays.deepToString(add(a, b)));
        System.out.println("transpose            = " + Arrays.deepToString(transpose(a)));
        System.out.println("multiply 2x3 by 3x2  = " + Arrays.deepToString(multiply(a, c)));
        try {
            multiply(a, b);
        } catch (IllegalArgumentException e) {
            System.out.println("multiply 2x3 by 2x3  = IllegalArgumentException: " + e.getMessage());
        }
        System.out.println("rowSums              = " + Arrays.toString(rowSums(a)));
        System.out.println("pascal(5)            = " + Arrays.deepToString(pascal(5)));
        int[][] grid = {{1, 0, 1}, {0, 1, 0}, {1, 1, 0}};
        System.out.println("neighbours corner, centre = " + neighbours(grid, 0, 0) + ", " + neighbours(grid, 1, 1));
        System.out.println("Arrays.toString(a) starts with [[I@ = " + Arrays.toString(a).startsWith("[[I@"));
        System.out.println("deepToString(a)      = " + Arrays.deepToString(a));
        System.out.println("sharedRowBug         = " + sharedRowBug());
        System.out.println("correctRows          = " + correctRows());
        System.out.println("shallowClone         = " + shallowClone());
        System.out.println("deepClone            = " + deepClone());
        System.out.println("unallocatedRow       = " + unallocatedRow());
    }
}
