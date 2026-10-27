public class F1 {
    public static void main(String[] args) {
        PaymentMethod method = new PaymentMethod();
    }
}

interface PaymentMethod {
    int fee(int price);
}
