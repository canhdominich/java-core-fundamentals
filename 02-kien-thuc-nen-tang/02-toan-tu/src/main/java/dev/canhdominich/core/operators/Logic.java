package dev.canhdominich.core.operators;

public class Logic {

    static int calls;

    static boolean tick(boolean value) {
        calls++;
        return value;
    }

    static int callsFor(java.util.function.BooleanSupplier expression) {
        calls = 0;
        expression.getAsBoolean();
        return calls;
    }

    public static boolean safeLength(String text) {
        return text != null && text.length() > 0;
    }

    public static boolean unsafeLength(String text) {
        return text != null & text.length() > 0;
    }

    public static void main(String[] args) {
        System.out.println("false && tick(true)  evaluates the right side " + callsFor(() -> false && tick(true)) + " times");
        System.out.println("false &  tick(true)  evaluates the right side " + callsFor(() -> false & tick(true)) + " times");
        System.out.println("true  || tick(false) evaluates the right side " + callsFor(() -> true || tick(false)) + " times");
        System.out.println("true  |  tick(false) evaluates the right side " + callsFor(() -> true | tick(false)) + " times");
        System.out.println("safeLength(null)     = " + safeLength(null));
        try {
            unsafeLength(null);
        } catch (NullPointerException e) {
            System.out.println("unsafeLength(null)   = NullPointerException (" + e.getMessage() + ")");
        }
        Integer small1 = 127;
        Integer small2 = 127;
        Integer big1 = 128;
        Integer big2 = 128;
        System.out.println("Integer 127 == 127   = " + (small1 == small2));
        System.out.println("Integer 128 == 128   = " + (big1 == big2) + ", equals = " + big1.equals(big2));
        System.out.println("!true == false       = " + (!true == false));
        System.out.println("true ^ true          = " + (true ^ true));
    }
}
