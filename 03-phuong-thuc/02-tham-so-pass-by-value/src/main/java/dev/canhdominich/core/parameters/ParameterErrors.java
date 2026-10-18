package dev.canhdominich.core.parameters;

public class ParameterErrors {

    public static void main(String[] args) {
        String notLast = "class A { void f(int... a, String b) { } }";
        String two = "class A { void f(int... a, int... b) { } }";
        String final_ = "class A { void f(final int x) { x = 5; } }";
        String duplicate = "class A { void f(int x, int x) { } }";
        String noType = "class A { void f(x) { } }";
        String mix = "class A { int g() { return f(1, \"two\"); } int f(int... values) { return values.length; } }";
        System.out.println("varargs not last        = " + Javac.messages("A", notLast));
        System.out.println("two varargs             = " + Javac.messages("A", two));
        System.out.println("assign to final param   = " + Javac.messages("A", final_));
        System.out.println("duplicate name          = " + Javac.messages("A", duplicate));
        System.out.println("parameter without type  = " + Javac.messages("A", noType));
        System.out.println("mixed argument types    = " + Javac.messages("A", mix));
    }
}
