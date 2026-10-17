package dev.canhdominich.core.operators;

public class Precedence {

    public static String grade(int score) {
        return score >= 80 ? "A" : score >= 65 ? "B" : score >= 50 ? "C" : "F";
    }

    public static void main(String[] args) {
        System.out.println("2 + 3 * 4            = " + (2 + 3 * 4));
        System.out.println("(2 + 3) * 4          = " + ((2 + 3) * 4));
        System.out.println("10 - 2 - 3           = " + (10 - 2 - 3));
        System.out.println("2 * 3 % 4            = " + (2 * 3 % 4));
        System.out.println("7 / 2 * 2.0          = " + (7 / 2 * 2.0));
        System.out.println("7 / (2 * 2.0)        = " + (7 / (2 * 2.0)));
        System.out.println("true || false && false      = " + (true || false && false));
        System.out.println("(true || false) && false    = " + ((true || false) && false));
        int a;
        int b;
        a = b = 5;
        System.out.println("a = b = 5            -> a=" + a + " b=" + b);
        System.out.println("1 + 2 + \"3\"          = " + (1 + 2 + "3"));
        System.out.println("\"1\" + 2 + 3          = " + ("1" + 2 + 3));
        System.out.println("\"1\" + (2 + 3)        = " + ("1" + (2 + 3)));
        System.out.println("grade(85), grade(70), grade(55), grade(10) = " + grade(85) + ", " + grade(70) + ", " + grade(55) + ", " + grade(10));
    }
}
