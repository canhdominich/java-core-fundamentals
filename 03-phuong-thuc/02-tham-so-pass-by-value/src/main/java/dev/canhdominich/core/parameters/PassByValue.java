package dev.canhdominich.core.parameters;

import java.util.Arrays;

public class PassByValue {

    public static void changeInt(int x) {
        x = 99;
    }

    public static void changeField(Box box) {
        box.value = 99;
    }

    public static void reassign(Box box) {
        box = new Box(99);
    }

    public static void swap(Box a, Box b) {
        Box temp = a;
        a = b;
        b = temp;
    }

    public static void swapContents(Box a, Box b) {
        int temp = a.value;
        a.value = b.value;
        b.value = temp;
    }

    public static boolean isSameObject(Box parameter, Box original) {
        return parameter == original;
    }

    public static String appendText(String text) {
        text += "!";
        return text;
    }

    public static void appendBuilder(StringBuilder builder) {
        builder.append("!");
    }

    public static void sortInPlace(int[] data) {
        Arrays.sort(data);
    }

    public static int[] sortedCopy(int[] data) {
        int[] copy = Arrays.copyOf(data, data.length);
        Arrays.sort(copy);
        return copy;
    }

    public static void main(String[] args) {
        int n = 5;
        changeInt(n);
        System.out.println("changeInt(5)               -> n = " + n);

        Box box = new Box(5);
        changeField(box);
        System.out.println("changeField(box)           -> " + box);

        Box other = new Box(5);
        reassign(other);
        System.out.println("reassign(box)              -> " + other);

        Box a = new Box(1);
        Box b = new Box(2);
        swap(a, b);
        System.out.println("swap(a, b)                 -> a = " + a + ", b = " + b);
        swapContents(a, b);
        System.out.println("swapContents(a, b)         -> a = " + a + ", b = " + b);

        System.out.println("parameter == original      -> " + isSameObject(a, a) + ", with a copy: " + isSameObject(new Box(a.value), a));

        String text = "hi";
        System.out.println("appendText(\"hi\")           -> returned " + appendText(text) + ", caller still has " + text);
        StringBuilder builder = new StringBuilder("hi");
        appendBuilder(builder);
        System.out.println("appendBuilder(\"hi\")        -> caller now has " + builder);

        int[] data = {3, 1, 2};
        int[] sorted = sortedCopy(data);
        System.out.println("sortedCopy(data)           -> data " + Arrays.toString(data) + ", result " + Arrays.toString(sorted));
        sortInPlace(data);
        System.out.println("sortInPlace(data)          -> data " + Arrays.toString(data));
    }
}
