package dev.canhdominich.core.loops;

import java.util.ArrayList;
import java.util.List;

public class LoopForms {

    public static List<Integer> forLoop(int from, int to) {
        List<Integer> values = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            values.add(i);
        }
        return values;
    }

    public static int whileLoop(int start) {
        int runs = 0;
        int i = start;
        while (i <= 5) {
            runs++;
            i++;
        }
        return runs;
    }

    public static int doWhileLoop(int start) {
        int runs = 0;
        int i = start;
        do {
            runs++;
            i++;
        } while (i <= 5);
        return runs;
    }

    public static String twoVariables() {
        StringBuilder pairs = new StringBuilder();
        for (int low = 0, high = 5; low < high; low++, high--) {
            pairs.append('(').append(low).append(',').append(high).append(')');
        }
        return pairs.toString();
    }

    public static String stepsOfThree() {
        StringBuilder values = new StringBuilder();
        for (int i = 10; i > 0; i -= 3) {
            values.append(i).append(' ');
        }
        return values.toString().trim();
    }

    public static int sumWithForEach(int[] numbers) {
        int total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;
    }

    public static int[] doubleWithForEach(int[] numbers) {
        int[] copy = numbers.clone();
        for (int number : copy) {
            number *= 2;
        }
        return copy;
    }

    public static int[] doubleWithIndex(int[] numbers) {
        int[] copy = numbers.clone();
        for (int i = 0; i < copy.length; i++) {
            copy[i] *= 2;
        }
        return copy;
    }

    public static void main(String[] args) {
        System.out.println("for 1..5                 = " + forLoop(1, 5));
        System.out.println("for 5..1                 = " + forLoop(5, 1));
        System.out.println("while starting at 1      = " + whileLoop(1) + " runs");
        System.out.println("while starting at 10     = " + whileLoop(10) + " runs");
        System.out.println("do-while starting at 1   = " + doWhileLoop(1) + " runs");
        System.out.println("do-while starting at 10  = " + doWhileLoop(10) + " runs");
        System.out.println("two variables            = " + twoVariables());
        System.out.println("steps of three           = " + stepsOfThree());
        int[] numbers = {10, 20, 30};
        System.out.println("sum with for-each        = " + sumWithForEach(numbers));
        System.out.println("double with for-each     = " + java.util.Arrays.toString(doubleWithForEach(numbers)));
        System.out.println("double with index        = " + java.util.Arrays.toString(doubleWithIndex(numbers)));
    }
}
