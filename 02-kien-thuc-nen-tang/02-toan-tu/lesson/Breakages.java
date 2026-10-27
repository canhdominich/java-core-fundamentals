public class Breakages {
    public static void main(String[] args) {
        double half = 1 / 2; // chia nguyên ra 0 rồi mới đổi thành 0.0
        System.out.println("1: " + half);
        int zero = 0;
        try {
            System.out.println(5 / zero);
        } catch (ArithmeticException e) {
            System.out.println("2: " + e);
        }
        String note = null;
        try {
            boolean unsafe = note != null & note.length() > 0; // một dấu & vẫn gọi length()
        } catch (NullPointerException e) {
            System.out.println("3: " + e.getMessage());
        }
    }
}
