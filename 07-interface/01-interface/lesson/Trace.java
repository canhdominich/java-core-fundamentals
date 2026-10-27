public class Trace {
    public static void main(String[] args) {
        PaymentMethod m = new Cash();
        int a = m.fee(50);
        m = new Card();
        int b = m.fee(50);
        System.out.println(a + " " + b);
    }
}

// Hợp đồng của quầy thu ngân: chỉ khai báo phần đầu, chưa có thân
interface PaymentMethod {
    String name();

    int fee(int price);
}

// Ký hợp đồng thì phải viết đủ mọi phương thức và ghi public
class Cash implements PaymentMethod {
    public String name() {
        return "Cash";
    }

    public int fee(int price) {
        return 0;
    }
}

class Card implements PaymentMethod {
    public String name() {
        return "Card";
    }

    public int fee(int price) {
        // Phí 2 phần trăm, chia số nguyên nên giá 100 cho ra 2
        return price * 2 / 100;
    }
}
