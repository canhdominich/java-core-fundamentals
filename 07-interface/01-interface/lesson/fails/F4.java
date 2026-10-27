public class F4 {
    public static void main(String[] args) {
        PaymentMethod method = new Card();
        System.out.println(method.last4());
    }
}

interface PaymentMethod {
    int fee(int price);
}

class Card implements PaymentMethod {
    public int fee(int price) {
        return price * 2 / 100;
    }

    public String last4() {
        return "1234";
    }
}
