import java.util.List;

public class Coupons {
    public static void main(String[] args) {
        Coupon a = new Coupon("TEA10");
        Coupon b = new Coupon("TEA10");
        System.out.println(a.equals(b));
        // contains gọi equals(Object), nên không thấy bản nhận Coupon
        System.out.println(List.of(a).contains(b));
    }
}

class Coupon {
    private final String code;

    Coupon(String code) {
        this.code = code;
    }

    // Đây là nạp chồng, không phải ghi đè equals(Object)
    public boolean equals(Coupon other) {
        return code.equals(other.code);
    }
}
