public class Selfcheck2 {
    public static void main(String[] args) {
        try {
            System.out.print("A");
            Integer.parseInt("x");
            System.out.print("B");
        } catch (ArithmeticException e) {
            System.out.print("C");
        } finally {
            System.out.print("D");
        }
        System.out.print("E");
    }
}
