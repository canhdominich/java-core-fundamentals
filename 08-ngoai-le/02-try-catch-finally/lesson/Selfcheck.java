public class Selfcheck {
    public static void main(String[] args) {
        try {
            System.out.print("A");
            int x = 1 / 0;
            System.out.print("B");
        } catch (ArithmeticException e) {
            System.out.print("C");
        } finally {
            System.out.print("D");
        }
        System.out.println("E");
    }
}
