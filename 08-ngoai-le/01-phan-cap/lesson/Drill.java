public class Drill {
    public static void main(String[] args) {
        System.out.println("A");
        first();
        System.out.println("B");
    }

    static void first() {
        second();
        System.out.println("C");
    }

    static void second() {
        // Chia cho 0 ném ArithmeticException, các dòng sau không chạy
        int x = 1 / 0;
        System.out.println("D");
    }
}
