public class K1 {
    public static void main(String[] args) {
        try {
            System.out.println(1 / 0);
        } catch (RuntimeException e) {
            System.out.println("general");
        } catch (ArithmeticException e) {
            System.out.println("specific");
        }
    }
}
