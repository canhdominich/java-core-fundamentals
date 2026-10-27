public class F2 {
    public static void main(String[] args) {
        PaymentMethod method = new Card();
    }
}

interface PaymentMethod {
    int fee(int price);
}

class Card implements PaymentMethod {
    int fee(int price) {
        return price * 2 / 100;
    }
}
