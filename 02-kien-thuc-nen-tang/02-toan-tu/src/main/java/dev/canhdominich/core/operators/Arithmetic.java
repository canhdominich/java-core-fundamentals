package dev.canhdominich.core.operators;

public class Arithmetic {

    public static String divideByZero() {
        try {
            return String.valueOf(5 / 0);
        } catch (ArithmeticException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        System.out.println("7 / 2                  = " + (7 / 2));
        System.out.println("7 / 2.0                = " + (7 / 2.0));
        System.out.println("(double) 7 / 2         = " + ((double) 7 / 2));
        System.out.println("(double) (7 / 2)       = " + (double) (7 / 2));
        System.out.println("7 % 3                  = " + (7 % 3));
        System.out.println("-7 % 3                 = " + (-7 % 3));
        System.out.println("Math.floorMod(-7, 3)   = " + Math.floorMod(-7, 3));
        System.out.println("-7 / 2                 = " + (-7 / 2));
        System.out.println("5.5 % 2                = " + (5.5 % 2));
        System.out.println("5 / 0                  = " + divideByZero());
        System.out.println("5.0 / 0                = " + (5.0 / 0));
        System.out.println("\"1\" + 2 + 3            = " + ("1" + 2 + 3));
        System.out.println("1 + 2 + \"3\"            = " + (1 + 2 + "3"));
        System.out.println("'a' + 1                = " + ('a' + 1));
        System.out.println("\"\" + 'a' + 1           = " + ("" + 'a' + 1));
        byte b = 10;
        b += 5;
        System.out.println("byte b = 10; b += 5    = " + b);
        b += 300;
        System.out.println("then b += 300          = " + b + "  (compound assignment casts silently)");
        int x = 10;
        x /= 4;
        System.out.println("int x = 10; x /= 4     = " + x);
        int y = 10;
        y *= 2.5;
        System.out.println("int y = 10; y *= 2.5   = " + y);
    }
}
