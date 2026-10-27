public class Reentrant {
    synchronized String outer() {
        // Cùng luồng giữ khoá này được vào lại phương thức khác của cùng đối tượng
        return "outer then " + inner();
    }

    synchronized String inner() {
        return "inner on the same lock";
    }

    public static void main(String[] args) {
        System.out.println(new Reentrant().outer());
    }
}
