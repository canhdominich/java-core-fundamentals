public class Combo {
    public static void main(String[] args) {
        CardPay card = new CardPay();
        // Hai biến kiểu khác nhau cùng trỏ một đối tượng, mỗi biến chỉ thấy phần hợp đồng của nó
        PaymentMethod method = card;
        Auditable audited = card;
        System.out.println(method.name() + " " + (100 + method.fee(100)) + " " + audited.audit());
    }
}

interface PaymentMethod {
    String name();
    int fee(int price);
}

interface Auditable {
    String audit();
}

// Lớp nền xây dở: có dữ liệu và code dùng chung, còn thiếu một phần
abstract class BasePayment implements PaymentMethod {
    // Mỗi đối tượng giữ tên riêng, interface không làm được việc này
    private final String name;

    BasePayment(String name) { this.name = name; }

    public String name() { return name; }
}

// Một lớp cha cộng thêm một hợp đồng
class CardPay extends BasePayment implements Auditable {
    CardPay() { super("Card"); }
    // Phí 2 phần trăm
    public int fee(int price) { return price * 2 / 100; }
    public String audit() { return "audited"; }
}
