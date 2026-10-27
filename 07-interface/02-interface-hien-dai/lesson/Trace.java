public class Trace {
    public static void main(String[] args) {
        PaymentMethod cash = new Cash();
        PaymentMethod card = new Card();
        System.out.println(cash.describe(250));
        System.out.println(card.describe(250));
        System.out.println(PaymentMethod.totalOf(card, 250));
    }
}

// Hợp đồng gồm phần trừu tượng, default và static
interface PaymentMethod {
    String name();
    int fee(int price);

    // default: có thân sẵn nên lớp cũ không phải sửa
    default String describe(int price) {
        // Chỉ gọi lại phương thức của hợp đồng, this là đối tượng thật
        return name() + " charges " + fee(price);
    }

    // static: thuộc về interface, gọi qua tên PaymentMethod
    static int totalOf(PaymentMethod method, int price) {
        return price + method.fee(price);
    }
}

class Cash implements PaymentMethod {
    public String name() { return "Cash"; }
    public int fee(int price) { return 0; }
}

class Card implements PaymentMethod {
    public String name() { return "Card"; }
    public int fee(int price) { return price * 2 / 100; }
    // Viết lại describe: lớp thắng default của interface
    public String describe(int price) { return "Card override: " + fee(price); }
}
