package dev.canhdominich.core.arrays;

import java.util.Arrays;

public class ArrayTraversal {

    public static int sum(int[] data) {
        int total = 0;
        for (int value : data) {
            total += value;
        }
        return total;
    }

    public static int maxOf(int[] data) {
        if (data.length == 0) {
            throw new IllegalArgumentException("array is empty");
        }
        int max = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }

    public static int indexOf(int[] data, int target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int[] reversed(int[] data) {
        int[] copy = data.clone();
        for (int low = 0, high = copy.length - 1; low < high; low++, high--) {
            int temp = copy[low];
            copy[low] = copy[high];
            copy[high] = temp;
        }
        return copy;
    }

    public static int[] rotateLeft(int[] data, int steps) {
        int n = data.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[(i - steps % n + n) % n] = data[i];
        }
        return result;
    }

    public static int[] runningSum(int[] data) {
        int[] result = new int[data.length];
        int total = 0;
        for (int i = 0; i < data.length; i++) {
            total += data[i];
            result[i] = total;
        }
        return result;
    }

    public static int[] histogram(String text) {
        int[] counts = new int[26];
        for (char c : text.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                counts[c - 'a']++;
            }
        }
        return counts;
    }

    public static int[] forEachCannotWrite(int[] data) {
        int[] copy = data.clone();
        for (int value : copy) {
            value = value * 2;
        }
        return copy;
    }

    public static void main(String[] args) {
        int[] data = {4, 9, 1, 7};
        System.out.println("sum                 = " + sum(data));
        System.out.println("maxOf               = " + maxOf(data));
        System.out.println("indexOf 7, 5        = " + indexOf(data, 7) + ", " + indexOf(data, 5));
        System.out.println("reversed            = " + Arrays.toString(reversed(data)));
        System.out.println("rotateLeft by 1, 5  = " + Arrays.toString(rotateLeft(data, 1)) + ", " + Arrays.toString(rotateLeft(data, 5)));
        System.out.println("runningSum          = " + Arrays.toString(runningSum(data)));
        int[] counts = histogram("Hello Java");
        System.out.println("histogram a, e, l, o = " + counts['a' - 'a'] + ", " + counts['e' - 'a'] + ", " + counts['l' - 'a'] + ", " + counts['o' - 'a']);
        System.out.println("for-each cannot write = " + Arrays.toString(forEachCannotWrite(data)));
    }
}
