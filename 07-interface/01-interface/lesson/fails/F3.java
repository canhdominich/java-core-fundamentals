public class F3 {
    public static void main(String[] args) {
        PaymentMethod method = new Card();
    }
}

interface PaymentMethod {
    String name();

    int fee(int price);
}

class Card implements PaymentMethod {
    public String name() {
        return "Card";
    }
}
