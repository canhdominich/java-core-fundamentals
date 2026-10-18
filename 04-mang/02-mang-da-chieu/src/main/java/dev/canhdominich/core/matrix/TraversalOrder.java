package dev.canhdominich.core.matrix;

public class TraversalOrder {

    public static long byRows(int[][] matrix) {
        long total = 0;
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                total += matrix[r][c];
            }
        }
        return total;
    }

    public static long byColumns(int[][] matrix) {
        long total = 0;
        for (int c = 0; c < matrix[0].length; c++) {
            for (int r = 0; r < matrix.length; r++) {
                total += matrix[r][c];
            }
        }
        return total;
    }

    public static void main(String[] args) {
        int size = 6000;
        int[][] matrix = new int[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                matrix[r][c] = r + c;
            }
        }
        long rowsBest = Long.MAX_VALUE;
        long columnsBest = Long.MAX_VALUE;
        long rowsTotal = 0;
        long columnsTotal = 0;
        for (int round = 0; round < 5; round++) {
            long start = System.nanoTime();
            rowsTotal = byRows(matrix);
            rowsBest = Math.min(rowsBest, System.nanoTime() - start);
            start = System.nanoTime();
            columnsTotal = byColumns(matrix);
            columnsBest = Math.min(columnsBest, System.nanoTime() - start);
        }
        System.out.println("same total by rows and by columns = " + (rowsTotal == columnsTotal));
        System.out.println("rows first, best of 5    = " + rowsBest / 1_000_000 + " ms");
        System.out.println("columns first, best of 5 = " + columnsBest / 1_000_000 + " ms");
        System.out.println("columns / rows           = " + String.format("%.1f", (double) columnsBest / rowsBest) + "x");
    }
}
