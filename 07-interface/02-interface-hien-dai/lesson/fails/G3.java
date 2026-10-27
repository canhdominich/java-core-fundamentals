public class G3 {
    public static void main(String[] args) {
        System.out.println("x");
    }
}

interface PaymentMethod {
    int fee(int price);

    default String describe(int price);
}
