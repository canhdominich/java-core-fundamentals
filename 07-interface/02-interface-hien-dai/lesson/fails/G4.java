public class G4 {
    public static void main(String[] args) {
        PaymentMethod cash = new Cash();
    }
}

interface PaymentMethod {
    int fee(int price);

    int refund(int price);
}

class Cash implements PaymentMethod {
    public int fee(int price) { return 0; }
}
