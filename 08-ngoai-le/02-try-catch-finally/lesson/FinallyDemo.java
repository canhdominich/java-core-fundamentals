public class FinallyDemo {
    public static void main(String[] args) {
        System.out.println(attempt());
        try {
            noMatch();
        } catch (NumberFormatException e) {
            System.out.println("outer caught " + e);
        }
    }

    static int attempt() {
        try {
            return 1;
        } finally {
            // Chạy trước khi lệnh return thật sự hoàn tất
            System.out.println("finally runs first");
        }
    }

    static void noMatch() {
        try {
            Integer.parseInt("x");
        } catch (ArithmeticException e) {
            System.out.println("not reached");
        } finally {
            // Không catch nào khớp, finally vẫn chạy rồi ngoại lệ đi tiếp lên trên
            System.out.println("finally still runs");
        }
    }
}
