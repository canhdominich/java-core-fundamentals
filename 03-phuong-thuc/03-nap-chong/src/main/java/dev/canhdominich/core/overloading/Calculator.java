package dev.canhdominich.core.overloading;

public class Calculator {

    public static int add(int a, int b) {
        return a + b;
    }

    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static String add(String a, String b) {
        return a + b;
    }

    public static String mixed(int a, double b) {
        return "int, double";
    }

    public static String mixed(double a, int b) {
        return "double, int";
    }

    public static void main(String[] args) {
        System.out.println("add(1, 2)           = " + add(1, 2));
        System.out.println("add(1, 2, 3)        = " + add(1, 2, 3));
        System.out.println("add(1.5, 2.5)       = " + add(1.5, 2.5));
        System.out.println("add(\"1\", \"2\")       = " + add("1", "2"));
        System.out.println("add(1, 2.5)         = " + add(1, 2.5));
        System.out.println("add('a', 'b')       = " + add('a', 'b'));
        System.out.println("mixed(1, 2.0)       = " + mixed(1, 2.0));
        System.out.println("mixed(1.0, 2)       = " + mixed(1.0, 2));
    }
}
