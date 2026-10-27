public class Summary {
    public static void main(String[] args) {
        PaymentMethod cash = new Cash();
        PaymentMethod card = new Card();
        PaymentMethod voucher = new VoucherPay(10);
        System.out.println(cash.summary(200));
        System.out.println(card.summary(200));
        System.out.println(voucher.summary(200));
        System.out.println("cheapest " + PaymentMethod.cheapestOf(cash, card, voucher, 200).name());
    }
}

// Hợp đồng gồm phần trừu tượng, default và static
interface PaymentMethod {
    String name();
    int fee(int price);

    default String summary(int price) {
        return name() + ": " + render(price, fee(price));
    }

    // private: chỉ dùng trong interface, lớp ký hợp đồng không thấy
    private String render(int price, int fee) {
        return price + " + " + fee + " = " + (price + fee);
    }

    static PaymentMethod cheapestOf(PaymentMethod a, PaymentMethod b, PaymentMethod c, int price) {
        // Ứng viên rẻ nhất tính tới lúc này
        PaymentMethod best = a;
        if (b.fee(price) < best.fee(price)) {
            best = b;
        }
        if (c.fee(price) < best.fee(price)) {
            best = c;
        }
        return best;
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

class VoucherPay implements PaymentMethod {
    private final int discount;

    VoucherPay(int discount) {
        this.discount = discount;
    }

    public String name() { return "Voucher"; }
    public int fee(int price) { return -discount; }
}
