public class H5 {
    public static void main(String[] args) {
        System.out.println(new CashPay().fee(10));
    }
}

abstract class BasePayment {
    private final String name;

    BasePayment(String name) { this.name = name; }

    abstract int fee(int price);
}

class CashPay extends BasePayment {
    int fee(int price) { return 0; }
}
