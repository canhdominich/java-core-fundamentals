import java.util.List;

public class Wallet {
    public static void main(String[] args) {
        List<PaymentMethod> bill = List.of(new Cash(), new Card(), new EWallet());
        int sum = 0;
        for (PaymentMethod method : bill) {
            sum += 200 + method.fee(200);
        }
        System.out.println("sum " + sum);
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

class EWallet implements PaymentMethod {
    public String name() {
        return "EWallet";
    }

    public int fee(int price) {
        return 1;
    }
}
