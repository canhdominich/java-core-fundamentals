public class CashierDemo {
    public static void main(String[] args) {
        // Biến kiểu hợp đồng, đối tượng thật là Cash
        PaymentMethod cash = new Cash();
        PaymentMethod card = new Card();
        checkout(cash, 100);
        checkout(card, 100);
    }

    // Chỉ phụ thuộc vào hợp đồng nên nhận được mọi cách thanh toán
    static void checkout(PaymentMethod method, int price) {
        // Java chạy fee của đối tượng thật phía sau biến method
        int total = price + method.fee(price);
        System.out.println(method.name() + " pays " + total);
    }
}

// Hợp đồng của quầy thu ngân: chỉ khai báo phần đầu, chưa có thân
interface PaymentMethod {
    String name();
    int fee(int price);
}

// Ký hợp đồng thì phải viết đủ mọi phương thức và ghi public
class Cash implements PaymentMethod {
    public String name() { return "Cash"; }
    public int fee(int price) { return 0; }
}

class Card implements PaymentMethod {
    public String name() { return "Card"; }
    // Phí 2 phần trăm, chia số nguyên nên giá 100 cho ra 2
    public int fee(int price) { return price * 2 / 100; }
}
