public class Voucher {
    public static void main(String[] args) {
        PaymentMethod[] methods = {new Cash(), new Card(), new VoucherPay(10)};
        for (PaymentMethod method : methods) {
            int total = 100 + method.fee(100);
            System.out.println(method.name() + " pays " + total);
        }
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
