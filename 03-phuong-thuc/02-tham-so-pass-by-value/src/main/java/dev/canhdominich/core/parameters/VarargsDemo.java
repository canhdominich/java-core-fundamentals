package dev.canhdominich.core.parameters;

import java.util.Arrays;

public class VarargsDemo {

    public static int sum(int... numbers) {
        int total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;
    }

    public static String describe(String label, int... values) {
        return label + ": " + values.length + " values " + Arrays.toString(values);
    }

    public static void zeroOut(int... values) {
        Arrays.fill(values, 0);
    }

    public static String chooseOverload(int a, int b) {
        return "two ints";
    }

    public static String chooseOverload(int... values) {
        return "varargs of " + values.length;
    }

    public static String nullArray() {
        try {
            return "sum = " + sum((int[]) null);
        } catch (NullPointerException e) {
            return "NullPointerException: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        System.out.println("sum()                      = " + sum());
        System.out.println("sum(7)                     = " + sum(7));
        System.out.println("sum(1, 2, 3)               = " + sum(1, 2, 3));
        System.out.println("sum(new int[] {4, 5})      = " + sum(new int[] {4, 5}));
        System.out.println("describe(\"none\")           = " + describe("none"));
        System.out.println("describe(\"three\", 1, 2, 3) = " + describe("three", 1, 2, 3));
        int[] original = {1, 2, 3};
        zeroOut(original);
        System.out.println("zeroOut(original)          -> original = " + Arrays.toString(original));
        System.out.println("chooseOverload(1, 2)       = " + chooseOverload(1, 2));
        System.out.println("chooseOverload(1, 2, 3)    = " + chooseOverload(1, 2, 3));
        System.out.println("chooseOverload()           = " + chooseOverload());
        System.out.println("sum((int[]) null)          = " + nullArray());
    }
}
