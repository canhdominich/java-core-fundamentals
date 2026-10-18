package dev.canhdominich.core.matrix;

public class MatrixErrors {

    public static void main(String[] args) {
        String missingFirst = "class A { int[][] m = new int[][3]; }";
        String tooManyIndexes = "class A { int f(int[][] m) { return m[0][0][0]; } }";
        String flatInit = "class A { int[][] m = {1, 2}; }";
        String assignRow = "class A { void f(int[][] m) { m[0] = 5; } }";
        String lengthOfElement = "class A { int f(int[][] m) { return m[0][0].length; } }";
        String jagged = "class A { int[][] m = {{1, 2}, {3}}; int f() { return m[1][1]; } }";
        System.out.println("size on the wrong side   = " + Javac.messages("A", missingFirst));
        System.out.println("one index too many       = " + Javac.messages("A", tooManyIndexes));
        System.out.println("flat values for 2D       = " + Javac.messages("A", flatInit));
        System.out.println("number into a row        = " + Javac.messages("A", assignRow));
        System.out.println("length of an int         = " + Javac.messages("A", lengthOfElement));
        System.out.println("jagged read compiles     = " + Javac.messages("A", jagged));
    }
}
