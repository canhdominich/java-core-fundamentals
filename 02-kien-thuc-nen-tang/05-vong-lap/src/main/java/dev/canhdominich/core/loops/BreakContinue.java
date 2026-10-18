package dev.canhdominich.core.loops;

import java.util.ArrayList;
import java.util.List;

public class BreakContinue {

    public static List<Integer> breakAt(int stop) {
        List<Integer> values = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            if (i == stop) {
                break;
            }
            values.add(i);
        }
        return values;
    }

    public static List<Integer> oddOnly(int limit) {
        List<Integer> values = new ArrayList<>();
        for (int i = 1; i <= limit; i++) {
            if (i % 2 == 0) {
                continue;
            }
            values.add(i);
        }
        return values;
    }

    public static String firstPairWithoutLabel(int[][] grid, int target) {
        String found = "none";
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == target) {
                    found = "(" + r + "," + c + ")";
                    break;
                }
            }
        }
        return found;
    }

    public static String firstPairWithLabel(int[][] grid, int target) {
        String found = "none";
        search:
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == target) {
                    found = "(" + r + "," + c + ")";
                    break search;
                }
            }
        }
        return found;
    }

    public static List<Integer> primesBelow(int limit) {
        List<Integer> primes = new ArrayList<>();
        candidates:
        for (int n = 2; n < limit; n++) {
            for (int d = 2; d * d <= n; d++) {
                if (n % d == 0) {
                    continue candidates;
                }
            }
            primes.add(n);
        }
        return primes;
    }

    public static int iterationsOf(int rows, int cols, boolean labeledBreak) {
        int iterations = 0;
        outer:
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                iterations++;
                if (r == 1 && c == 1) {
                    if (labeledBreak) {
                        break outer;
                    }
                    break;
                }
            }
        }
        return iterations;
    }

    public static void main(String[] args) {
        System.out.println("breakAt(5)               = " + breakAt(5));
        System.out.println("oddOnly(9)               = " + oddOnly(9));
        int[][] grid = {{4, 9, 4}, {7, 4, 1}, {4, 2, 8}};
        System.out.println("first 4, break only      = " + firstPairWithoutLabel(grid, 4));
        System.out.println("first 4, break with label= " + firstPairWithLabel(grid, 4));
        System.out.println("primesBelow(30)          = " + primesBelow(30));
        System.out.println("iterations, plain break  = " + iterationsOf(3, 3, false));
        System.out.println("iterations, labeled break= " + iterationsOf(3, 3, true));
    }
}
