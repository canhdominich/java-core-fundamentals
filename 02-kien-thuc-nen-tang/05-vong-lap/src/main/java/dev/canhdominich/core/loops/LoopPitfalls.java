package dev.canhdominich.core.loops;

import java.util.ArrayList;
import java.util.List;

public class LoopPitfalls {

    public static String offByOne(int[] data) {
        int total = 0;
        try {
            for (int i = 0; i <= data.length; i++) {
                total += data[i];
            }
            return "total " + total;
        } catch (ArrayIndexOutOfBoundsException e) {
            return "total so far " + total + ", then " + e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }

    public static int strayCount() {
        int runs = 0;
        for (int i = 0; i < 10; i++);
        {
            runs++;
        }
        return runs;
    }

    public static List<Double> floatCounter(int cap) {
        List<Double> seen = new ArrayList<>();
        for (double d = 0; d != 1.0 && seen.size() < cap; d += 0.1) {
            seen.add(d);
        }
        return seen;
    }

    public static int byteCounter(int cap) {
        int runs = 0;
        for (byte b = 0; b < 200 && runs < cap; b++) {
            runs++;
        }
        return runs;
    }

    public static int shrinkingBound() {
        List<Integer> values = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        int visited = 0;
        for (int i = 0; i < values.size(); i++) {
            values.remove(i);
            visited++;
        }
        return visited;
    }

    public static void main(String[] args) {
        System.out.println("offByOne                 = " + offByOne(new int[] {5, 6, 7}));
        System.out.println("strayCount               = " + strayCount());
        List<Double> seen = floatCounter(14);
        System.out.println("floatCounter first 12    = " + seen.subList(0, 12));
        System.out.println("floatCounter reached cap = " + seen.size() + " runs without hitting 1.0");
        System.out.println("byteCounter(1000)        = " + byteCounter(1000) + " runs, although 200 would be the limit");
        System.out.println("shrinkingBound           = visited " + shrinkingBound() + " of 6 elements");
        String scope = "class A { void f() { for (int i = 0; i < 3; i++) { } System.out.println(i); } }";
        String missing = "class A { void f() { for (int i = 0; i < 3; i++ { } } }";
        String outside = "class A { void f(int x) { if (x > 0) { break; } } }";
        String noLabel = "class A { void f() { for (int i = 0; i < 3; i++) { break search; } } }";
        System.out.println("variable out of scope    = " + Javac.messages("A", scope));
        System.out.println("missing parenthesis      = " + Javac.messages("A", missing));
        System.out.println("break outside a loop     = " + Javac.messages("A", outside));
        System.out.println("label that does not exist= " + Javac.messages("A", noLabel));
    }
}
