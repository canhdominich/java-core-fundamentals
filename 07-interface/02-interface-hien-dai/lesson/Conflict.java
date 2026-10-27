public class Conflict {
    public static void main(String[] args) {
        System.out.println(new Member().badge());
    }
}

interface Loyalty {
    // Hai interface cùng có default badge() nên lớp ký cả hai phải tự chọn
    default String badge() { return "member"; }
}

interface Promo {
    default String badge() { return "promo"; }
}

class Member implements Loyalty, Promo {
    public String badge() {
        // X.super.badge() gọi đúng thân default của interface X
        return Loyalty.super.badge() + "+" + Promo.super.badge();
    }
}
