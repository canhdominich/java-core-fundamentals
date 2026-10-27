public class BaseDemo {
    public static void main(String[] args) {
        // Biến kiểu lớp nền, đối tượng thật là CashPay
        BasePayment cash = new CashPay();
        BasePayment card = new CardPay();
        System.out.println(cash.receipt(100));
        System.out.println(card.receipt(100));
    }
}

// Lớp nền xây dở: có dữ liệu và code dùng chung, còn thiếu một phần
abstract class BasePayment {
    // Mỗi đối tượng giữ tên riêng, interface không làm được việc này
    private final String name;

    BasePayment(String name) { this.name = name; }

    // Chưa có thân: mỗi lớp con phải tự viết
    abstract int fee(int price);

    // Viết một lần, gọi fee của lớp con thật khi chạy
    String receipt(int price) { return name + " total " + (price + fee(price)); }
}

class CashPay extends BasePayment {
    // super(...) phải là dòng đầu, truyền tên lên lớp cha
    CashPay() { super("Cash"); }
    int fee(int price) { return 0; }
}

class CardPay extends BasePayment {
    CardPay() { super("Card"); }
    // Phí 2 phần trăm
    int fee(int price) { return price * 2 / 100; }
}
