package dev.canhdominich.core.arraytools;

import java.util.Arrays;

public class ArraysTour {

    public static String sorting() {
        int[] data = {9, 3, 7, 1, 5};
        int[] ranged = data.clone();
        Arrays.sort(data);
        Arrays.sort(ranged, 1, 4);
        return "sorted " + Arrays.toString(data) + ", only indexes 1 to 3 " + Arrays.toString(ranged);
    }

    public static String binarySearch() {
        int[] sorted = {10, 20, 30, 40, 50};
        return "find 30 -> " + Arrays.binarySearch(sorted, 30)
                + ", find 5 -> " + Arrays.binarySearch(sorted, 5)
                + ", find 35 -> " + Arrays.binarySearch(sorted, 35)
                + ", find 99 -> " + Arrays.binarySearch(sorted, 99);
    }

    public static String binarySearchOnUnsorted() {
        int[] data = {50, 10, 40, 20, 30};
        return "find 30 in an unsorted array -> " + Arrays.binarySearch(data, 30);
    }

    public static int insertionPoint(int[] sorted, int value) {
        int result = Arrays.binarySearch(sorted, value);
        return result >= 0 ? result : -result - 1;
    }

    public static String fillAndCopy() {
        int[] data = new int[5];
        Arrays.fill(data, 7);
        int[] part = new int[5];
        Arrays.fill(part, 1, 3, 9);
        return "fill " + Arrays.toString(data) + ", fill indexes 1 to 2 " + Arrays.toString(part);
    }

    public static String comparing() {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        int[][] nestedA = {{1, 2}, {3}};
        int[][] nestedB = {{1, 2}, {3}};
        return "a == b " + (a == b) + ", equals " + a.equals(b) + ", Arrays.equals " + Arrays.equals(a, b)
                + ", nested Arrays.equals " + Arrays.equals(nestedA, nestedB) + ", deepEquals " + Arrays.deepEquals(nestedA, nestedB)
                + ", same hashCode " + (Arrays.hashCode(a) == Arrays.hashCode(b));
    }

    public static String mismatchAndCompare() {
        int[] a = {1, 2, 3, 4};
        int[] b = {1, 2, 9, 4};
        return "mismatch at " + Arrays.mismatch(a, b) + ", mismatch of equal arrays " + Arrays.mismatch(a, a.clone())
                + ", compare " + Arrays.compare(a, b) + ", compare reversed " + Arrays.compare(b, a);
    }

    public static String printing() {
        int[] none = null;
        return "null array -> " + Arrays.toString(none) + ", empty -> " + Arrays.toString(new int[0]) + ", nested -> " + Arrays.deepToString(new int[][] {{1}, {2, 3}, {}});
    }

    public static void main(String[] args) {
        System.out.println("sorting            = " + sorting());
        System.out.println("binarySearch       = " + binarySearch());
        System.out.println("unsorted search    = " + binarySearchOnUnsorted());
        System.out.println("insertionPoint(35) = " + insertionPoint(new int[] {10, 20, 30, 40, 50}, 35));
        System.out.println("fill               = " + fillAndCopy());
        System.out.println("comparing          = " + comparing());
        System.out.println("mismatch, compare  = " + mismatchAndCompare());
        System.out.println("printing           = " + printing());
    }
}
