package dev.canhdominich.core.arrays;

public class ArrayErrors {

    public static void main(String[] args) {
        String both = "class A { int[] f() { return new int[3] {1, 2, 3}; } }";
        String sizeInType = "class A { int a[3]; }";
        String lengthCall = "class A { int f(int[] a) { return a.length(); } }";
        String wrongElement = "class A { int[] a = {1, \"2\"}; }";
        String assignLength = "class A { void f(int[] a) { a.length = 5; } }";
        String missingSize = "class A { int[] a = new int[]; }";
        System.out.println("size and values together = " + Javac.messages("A", both));
        System.out.println("size in the declaration  = " + Javac.messages("A", sizeInType));
        System.out.println("length() with brackets   = " + Javac.messages("A", lengthCall));
        System.out.println("wrong element type       = " + Javac.messages("A", wrongElement));
        System.out.println("assign to length         = " + Javac.messages("A", assignLength));
        System.out.println("new without a size       = " + Javac.messages("A", missingSize));
    }
}
