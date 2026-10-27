public class G5 {
    public static void main(String[] args) {
        System.out.println(new Cash().summary(100));
    }
}

interface PaymentMethod {
    int fee(int price);

    default String summary(int price) {
        return render(price);
    }

    private String render(int price) {
        return "total " + (price + fee(price));
    }
}

class Cash implements PaymentMethod {
    public int fee(int price) { return 0; }

    public String again(int price) {
        return render(price);
    }
}
