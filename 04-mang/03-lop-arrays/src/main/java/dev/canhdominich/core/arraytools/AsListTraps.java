package dev.canhdominich.core.arraytools;

import java.util.Arrays;
import java.util.List;

public class AsListTraps {

    public static String writeThrough() {
        String[] names = {"An", "Binh", "Chi"};
        List<String> view = Arrays.asList(names);
        view.set(0, "Anh");
        names[2] = "Cuong";
        return "list " + view + ", array " + Arrays.toString(names);
    }

    public static String fixedSize() {
        List<String> view = Arrays.asList("An", "Binh");
        try {
            view.add("Chi");
            return "added";
        } catch (UnsupportedOperationException e) {
            return "UnsupportedOperationException, message " + e.getMessage();
        }
    }

    public static String primitiveArray() {
        int[] numbers = {1, 2, 3};
        List<int[]> wrong = Arrays.asList(numbers);
        Integer[] boxed = {1, 2, 3};
        List<Integer> right = Arrays.asList(boxed);
        return "from int[] the list size is " + wrong.size() + ", from Integer[] the list size is " + right.size();
    }

    public static String immutableCopy() {
        String[] names = {"An", "Binh"};
        List<String> copy = List.of(names);
        names[0] = "Changed";
        return "List.of copy " + copy + ", array " + Arrays.toString(names);
    }

    public static void main(String[] args) {
        System.out.println("writeThrough   = " + writeThrough());
        System.out.println("fixedSize      = " + fixedSize());
        System.out.println("primitiveArray = " + primitiveArray());
        System.out.println("immutableCopy  = " + immutableCopy());
    }
}
