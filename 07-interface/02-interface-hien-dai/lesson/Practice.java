public class Practice {
    public static void main(String[] args) {
        PaymentMethod cash = new Cash();
        PaymentMethod card = new Card();
        System.out.println("step 1: " + cash.receiptLine(100));
        System.out.println("step 2: " + PaymentMethod.cheaper(cash, card, 100).name());
        System.out.println("step 3: " + cash.isFree(100) + " " + card.isFree(100));
        System.out.println("step 4: " + new Member().badge());
    }
}

// Hợp đồng gồm phần trừu tượng, default và static
interface PaymentMethod {
    String name();
    int fee(int price);

    default String receiptLine(int price) {
        // this là đối tượng thật nên fee đúng của lớp được dùng
        return name() + ": " + totalOf(this, price);
    }

    default boolean isFree(int price) {
        return fee(price) <= 0;
    }

    // static: thuộc về interface, gọi qua tên PaymentMethod
    static int totalOf(PaymentMethod method, int price) {
        return price + method.fee(price);
    }

    static PaymentMethod cheaper(PaymentMethod a, PaymentMethod b, int price) {
        // Hòa thì chọn a
        return totalOf(a, price) <= totalOf(b, price) ? a : b;
    }
}

class Cash implements PaymentMethod {
    public String name() { return "Cash"; }
    public int fee(int price) { return 0; }
}

class Card implements PaymentMethod {
    public String name() { return "Card"; }
    public int fee(int price) { return price * 2 / 100; }
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
