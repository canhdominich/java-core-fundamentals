public class Boom {
    public static void main(String[] args) {
        System.out.println("about to fail");
        // Ném ngoại lệ không ai bắt: chương trình dừng với mã thoát 1
        throw new RuntimeException("boom");
    }
}
