package dev.canhdominich.core.arrays;

import java.util.Arrays;

public class ArrayCopies {

    public record Point(int x, int y) {
    }

    public static class Cell {
        public int value;

        public Cell(int value) {
            this.value = value;
        }
    }

    public static String alias() {
        int[] a = {1, 2, 3};
        int[] b = a;
        b[0] = 99;
        return "a = " + Arrays.toString(a) + ", same array: " + (a == b);
    }

    public static String cloned() {
        int[] a = {1, 2, 3};
        int[] c = a.clone();
        c[1] = 77;
        return "a = " + Arrays.toString(a) + ", c = " + Arrays.toString(c);
    }

    public static String copyOf() {
        int[] a = {1, 2, 3};
        return "bigger " + Arrays.toString(Arrays.copyOf(a, 5)) + ", smaller " + Arrays.toString(Arrays.copyOf(a, 2))
                + ", range " + Arrays.toString(Arrays.copyOfRange(a, 1, 3));
    }

    public static String arraycopy() {
        int[] source = {1, 2, 3, 4, 5};
        int[] target = new int[7];
        System.arraycopy(source, 1, target, 2, 3);
        return Arrays.toString(target);
    }

    public static String overlappingArraycopy() {
        int[] data = {1, 2, 3, 4, 5};
        System.arraycopy(data, 0, data, 1, 4);
        return Arrays.toString(data);
    }

    public static String arraycopyTooLong() {
        try {
            System.arraycopy(new int[3], 0, new int[2], 0, 3);
            return "copied";
        } catch (ArrayIndexOutOfBoundsException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }

    public static String shallowCopy() {
        Cell[] original = {new Cell(1), new Cell(2)};
        Cell[] copy = original.clone();
        copy[0].value = 99;
        copy[1] = new Cell(50);
        return "original values " + original[0].value + ", " + original[1].value + " | copy values " + copy[0].value + ", " + copy[1].value
                + " | same first element: " + (original[0] == copy[0]);
    }

    public static String deepCopy() {
        Cell[] original = {new Cell(1), new Cell(2)};
        Cell[] copy = new Cell[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = new Cell(original[i].value);
        }
        copy[0].value = 99;
        return "original first " + original[0].value + ", copy first " + copy[0].value;
    }

    public static void main(String[] args) {
        System.out.println("alias               = " + alias());
        System.out.println("clone               = " + cloned());
        System.out.println("copyOf              = " + copyOf());
        System.out.println("arraycopy           = " + arraycopy());
        System.out.println("overlapping         = " + overlappingArraycopy());
        System.out.println("too long            = " + arraycopyTooLong());
        System.out.println("shallow copy        = " + shallowCopy());
        System.out.println("deep copy           = " + deepCopy());
    }
}
