import java.util.List;
import java.util.Set;

public class CouponsFixed {
    public static void main(String[] args) {
        Coupon a = new Coupon("TEA10");
        Coupon b = new Coupon("TEA10");
        System.out.println(a.equals(b));
        System.out.println(List.of(a).contains(b));
        System.out.println(Set.of(a).contains(b));
    }
}

class Coupon {
    private final String code;

    Coupon(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        // Kiểm tra kiểu trước, rồi so sánh nội dung
        return o instanceof Coupon other && code.equals(other.code);
    }

    @Override
    public int hashCode() {
        // Cùng dữ liệu với equals để HashSet tìm đúng ngăn
        return code.hashCode();
    }
}
