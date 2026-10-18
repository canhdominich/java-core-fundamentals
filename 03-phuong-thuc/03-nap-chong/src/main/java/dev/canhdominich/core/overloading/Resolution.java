package dev.canhdominich.core.overloading;

public class Resolution {

    static String widening(long x) {
        return "long";
    }

    static String widening(Integer x) {
        return "Integer";
    }

    static String widening(Object x) {
        return "Object";
    }

    static String widening(int... x) {
        return "int...";
    }

    static String boxing(Integer x) {
        return "Integer";
    }

    static String boxing(Object x) {
        return "Object";
    }

    static String boxing(int... x) {
        return "int...";
    }

    static String onlyVarargs(int... x) {
        return "int... with " + x.length;
    }

    static String onlyVarargs(Object o) {
        return "Object";
    }

    public static void main(String[] args) {
        System.out.println("widening(5)          = " + widening(5));
        System.out.println("widening('a')        = " + widening('a'));
        System.out.println("widening(5.0)        = " + widening(5.0));
        System.out.println("widening(Integer 5)  = " + widening(Integer.valueOf(5)));
        System.out.println("widening()           = " + widening());
        System.out.println("widening(1, 2)       = " + widening(1, 2));
        System.out.println("boxing(5)            = " + boxing(5));
        System.out.println("boxing(5L)           = " + boxing(5L));
        System.out.println("boxing(5, 6)         = " + boxing(5, 6));
        System.out.println("onlyVarargs(5)       = " + onlyVarargs(5));
        System.out.println("onlyVarargs()        = " + onlyVarargs());
    }
}
