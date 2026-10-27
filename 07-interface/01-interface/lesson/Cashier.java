public class Cashier {
    public static void main(String[] args) {
        PaymentMethod[] methods = {new Cash(), new Card(), new VoucherPay(10), new EWallet()};
        System.out.println("total " + total(methods, 200));
        System.out.println("cheapest " + cheapest(methods, 200).name());
    }

    static int total(PaymentMethod[] methods, int price) {
        int sum = 0;
        for (PaymentMethod method : methods) {
            // Java chạy fee của đối tượng thật phía sau biến method
            sum += price + method.fee(price);
        }
        return sum;
    }

    static PaymentMethod cheapest(PaymentMethod[] methods, int price) {
        // Ứng viên rẻ nhất tính tới lúc này
        PaymentMethod best = methods[0];
        for (PaymentMethod method : methods) {
            if (method.fee(price) < best.fee(price)) {
                best = method;
            }
        }
        return best;
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

class VoucherPay implements PaymentMethod {
    private final int discount;

    VoucherPay(int discount) {
        // Lưu giảm giá vào trường của đối tượng
        this.discount = discount;
    }

    public String name() {
        return "Voucher";
    }

    public int fee(int price) {
        // Giảm giá được biểu diễn bằng phí âm
        return -discount;
    }
}

class EWallet implements PaymentMethod {
    public String name() {
        return "EWallet";
    }

    public int fee(int price) {
        return 1;
    }
}
