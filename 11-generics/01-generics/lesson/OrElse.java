public class OrElse {
    static <T> T orElse(T value, T fallback) {
        // Cả hai tham số cùng nhãn T nên phải cùng kiểu
        return value == null ? fallback : value;
    }

    public static void main(String[] args) {
        System.out.println(orElse(null, "none"));
        System.out.println(orElse(5, 9));
    }
}
