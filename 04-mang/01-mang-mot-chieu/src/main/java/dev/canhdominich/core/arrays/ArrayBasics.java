package dev.canhdominich.core.arrays;

import java.util.Arrays;

public class ArrayBasics {

    public static String defaults() {
        int[] numbers = new int[2];
        double[] decimals = new double[2];
        boolean[] flags = new boolean[2];
        char[] letters = new char[2];
        String[] words = new String[2];
        return "int " + Arrays.toString(numbers) + ", double " + Arrays.toString(decimals) + ", boolean " + Arrays.toString(flags)
                + ", char codes " + (int) letters[0] + ", String " + Arrays.toString(words);
    }

    public static String outOfBounds(int index) {
        int[] data = {10, 20, 30};
        try {
            return "data[" + index + "] = " + data[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }

    public static String negativeSize(int size) {
        try {
            return "length " + new int[size].length;
        } catch (NegativeArraySizeException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }

    public static String nullElement() {
        String[] words = new String[2];
        try {
            return "length " + words[0].length();
        } catch (NullPointerException e) {
            return "NullPointerException on an element that was never assigned";
        }
    }

    public static String nullArray() {
        int[] data = null;
        try {
            return "length " + data.length;
        } catch (NullPointerException e) {
            return "NullPointerException: " + e.getMessage();
        }
    }

    public static String tooBig() {
        try {
            int[] huge = new int[Integer.MAX_VALUE];
            return "allocated " + huge.length;
        } catch (OutOfMemoryError e) {
            return "OutOfMemoryError: " + e.getMessage();
        }
    }

    public static String printedDirectly() {
        int[] data = {1, 2, 3};
        String text = data.toString();
        return text.substring(0, 3) + "... versus " + Arrays.toString(data);
    }

    public static void main(String[] args) {
        System.out.println("defaults                = " + defaults());
        System.out.println("outOfBounds(1)          = " + outOfBounds(1));
        System.out.println("outOfBounds(3)          = " + outOfBounds(3));
        System.out.println("outOfBounds(-1)         = " + outOfBounds(-1));
        System.out.println("negativeSize(-1)        = " + negativeSize(-1));
        System.out.println("negativeSize(0)         = " + negativeSize(0));
        System.out.println("nullElement()           = " + nullElement());
        System.out.println("nullArray()             = " + nullArray());
        System.out.println("tooBig()                = " + tooBig());
        System.out.println("printed directly        = " + printedDirectly());
        int[] first = {1, 2, 3};
        int[] second = {1, 2, 3};
        System.out.println("first == second         = " + (first == second));
        System.out.println("first.equals(second)    = " + first.equals(second));
        System.out.println("Arrays.equals          = " + Arrays.equals(first, second));
    }
}
