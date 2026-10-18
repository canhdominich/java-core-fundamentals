package dev.canhdominich.core.oop.constructors;

public class ConstructorErrors {

    public static void main(String[] args) {
        String lostDefault = "class P { P(int x) { } } class A { P p = new P(); }";
        String notFirst = "class P { P() { int a = 1; this(2); } P(int x) { } }";
        String recursive = "class P { P() { this(1); } P(int x) { this(); } }";
        String returnValue = "class P { P() { return 5; } }";
        String finalMissing = "class P { final int x; P() { } }";
        String superMissing = "class B { B(int x) { } } class C extends B { C() { } }";
        String methodNotConstructor = "class P { void P() { } } class A { P p = new P(); }";
        String ambiguousNull = "class A { Object f() { return new dev.canhdominich.core.oop.constructors.Person(null); } }";
        System.out.println("default constructor gone  = " + Javac.messages("A", lostDefault));
        System.out.println("this() not first          = " + Javac.messages("P", notFirst));
        System.out.println("recursive this()          = " + Javac.messages("P", recursive));
        System.out.println("constructor returns value = " + Javac.messages("P", returnValue));
        System.out.println("final field not set       = " + Javac.messages("P", finalMissing));
        System.out.println("super() without arguments = " + Javac.messages("C", superMissing));
        System.out.println("void P() is a method      = " + Javac.messages("A", methodNotConstructor));
        System.out.println("new Person(null)          = " + Javac.messages("A", ambiguousNull));
    }
}
