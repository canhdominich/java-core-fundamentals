public class H1 {
    public static void main(String[] args) {
        BasePayment p = new BasePayment("Cash");
    }
}

abstract class BasePayment {
    private final String name;

    BasePayment(String name) { this.name = name; }

    abstract int fee(int price);
}
