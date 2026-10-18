package dev.canhdominich.core.methods;

public class MethodErrors {

    public static void main(String[] args) {
        String missingReturn = "class A { int f(int x) { if (x > 0) { return 1; } } }";
        String unreachable = "class A { int f() { return 1; System.out.println(2); } }";
        String voidValue = "class A { void log() { } void g() { int x = log(); } }";
        String tooFew = "class A { int add(int a, int b) { return a + b; } int g() { return add(1); } }";
        String wrongType = "class A { int twice(int n) { return n * 2; } int g() { return twice(\"4\"); } }";
        String noReturnType = "class A { compute(int n) { return n; } }";
        System.out.println("missing return       = " + Javac.messages("A", missingReturn));
        System.out.println("code after return    = " + Javac.messages("A", unreachable));
        System.out.println("void used as a value = " + Javac.messages("A", voidValue));
        System.out.println("too few arguments    = " + Javac.messages("A", tooFew));
        System.out.println("wrong argument type  = " + Javac.messages("A", wrongType));
        System.out.println("no return type       = " + Javac.messages("A", noReturnType));
    }
}
