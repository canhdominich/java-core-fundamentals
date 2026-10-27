public class G2 {
    public static void main(String[] args) {
        PaymentMethod card = new Card();
        System.out.println(card.totalOf(card, 100));
    }
}

interface PaymentMethod {
    int fee(int price);

    static int totalOf(PaymentMethod method, int price) {
        return price + method.fee(price);
    }
}

class Card implements PaymentMethod {
    public int fee(int price) { return price * 2 / 100; }
}
