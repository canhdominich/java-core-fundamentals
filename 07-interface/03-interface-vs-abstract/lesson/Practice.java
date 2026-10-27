public class Practice {
    public static void main(String[] args) {
        BasePayment voucher = new VoucherPay(10);
        System.out.println("step 1: " + voucher.receipt(100));
        System.out.println("step 2: " + voucher.isFree(100) + " " + new CardPay().isFree(100));
        BasePayment[] all = {new CashPay(), new CardPay(), voucher};
        int sum = 0;
        for (BasePayment p : all) {
            sum += p.total(100);
        }
        System.out.println("step 3: " + sum);
    }
}

// Lớp nền xây dở: có dữ liệu và code dùng chung, còn thiếu một phần
abstract class BasePayment {
    // Mỗi đối tượng giữ tên riêng, interface không làm được việc này
    private final String name;

    BasePayment(String name) { this.name = name; }

    // Chưa có thân: mỗi lớp con phải tự viết
    abstract int fee(int price);

    int total(int price) { return price + fee(price); }

    // Viết một lần ở lớp nền cho mọi lớp con
    boolean isFree(int price) { return fee(price) <= 0; }

    // Viết một lần, gọi fee của lớp con thật khi chạy
    String receipt(int price) { return name + " total " + total(price); }
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

class VoucherPay extends BasePayment {
    private final int discount;

    // Lớp con có thêm trường riêng ngoài name của lớp cha
    VoucherPay(int discount) {
        super("Voucher");
        this.discount = discount;
    }

    int fee(int price) { return -discount; }
}
