package dev.canhdominich.core.arraytools;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SortingChoices {

    public static String stableByLength() {
        String[] words = {"bb", "aa", "c", "dd", "e", "ff"};
        Arrays.sort(words, Comparator.comparingInt(String::length));
        return Arrays.toString(words);
    }

    public static String caseInsensitive() {
        String[] words = {"banana", "apple", "Cherry", "Apple"};
        String[] natural = words.clone();
        Arrays.sort(natural);
        String[] folded = words.clone();
        Arrays.sort(folded, String.CASE_INSENSITIVE_ORDER);
        return "natural " + Arrays.toString(natural) + ", ignoring case " + Arrays.toString(folded);
    }

    public static String descending() {
        Integer[] boxed = {3, 9, 1, 7};
        Arrays.sort(boxed, Collections.reverseOrder());
        return Arrays.toString(boxed);
    }

    public static String reverseAnIntArray() {
        int[] data = {3, 9, 1, 7};
        Arrays.sort(data);
        for (int low = 0, high = data.length - 1; low < high; low++, high--) {
            int temp = data[low];
            data[low] = data[high];
            data[high] = temp;
        }
        return Arrays.toString(data);
    }

    public static void main(String[] args) {
        System.out.println("stable sort by length = " + stableByLength());
        System.out.println("strings               = " + caseInsensitive());
        System.out.println("descending Integer[]  = " + descending());
        System.out.println("descending int[]      = " + reverseAnIntArray());
        String noComparator = "import java.util.*; class A { void f(int[] a) { Arrays.sort(a, Comparator.reverseOrder()); } }";
        System.out.println("int[] with a Comparator = " + Javac.messages("A", noComparator));
    }
}
