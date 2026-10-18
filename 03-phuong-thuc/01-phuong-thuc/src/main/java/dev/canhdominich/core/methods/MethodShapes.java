package dev.canhdominich.core.methods;

import java.util.ArrayList;
import java.util.List;

public class MethodShapes {

    public record Range(int min, int max) {
    }

    static final List<String> trace = new ArrayList<>();

    public static double average(int[] data) {
        int total = 0;
        for (int value : data) {
            total += value;
        }
        return (double) total / data.length;
    }

    public static Range rangeOf(int[] data) {
        int min = data[0];
        int max = data[0];
        for (int value : data) {
            min = Math.min(min, value);
            max = Math.max(max, value);
        }
        return new Range(min, max);
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public static String describeNested(int age, boolean member) {
        String result;
        if (age >= 0) {
            if (age < 130) {
                if (member) {
                    result = "member, " + age;
                } else {
                    result = "guest, " + age;
                }
            } else {
                result = "invalid age";
            }
        } else {
            result = "invalid age";
        }
        return result;
    }

    public static String describeGuarded(int age, boolean member) {
        if (age < 0 || age >= 130) {
            return "invalid age";
        }
        if (member) {
            return "member, " + age;
        }
        return "guest, " + age;
    }

    static int mark(String name, int value) {
        trace.add(name);
        return value;
    }

    static int sum3(int a, int b, int c) {
        trace.add("sum3");
        return a + b + c;
    }

    public static List<String> evaluationOrder() {
        trace.clear();
        sum3(mark("first", 1), mark("second", 2), mark("third", 3));
        return new ArrayList<>(trace);
    }

    public static void main(String[] args) {
        int[] data = {4, 9, 1, 7};
        System.out.println("average            = " + average(data));
        System.out.println("rangeOf            = " + rangeOf(data));
        System.out.println("isLeapYear 1900, 2000, 2024 = " + isLeapYear(1900) + ", " + isLeapYear(2000) + ", " + isLeapYear(2024));
        System.out.println("nested vs guarded  = " + describeNested(30, true) + " | " + describeGuarded(30, true));
        System.out.println("invalid age        = " + describeNested(200, false) + " | " + describeGuarded(-1, false));
        System.out.println("evaluation order   = " + evaluationOrder());
        System.out.println("average of empty   = " + average(new int[0]));
    }
}
