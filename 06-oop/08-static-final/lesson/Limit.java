public class Limit {
    public static void main(String[] args) {
        System.out.println(new Invoice(700).number());
        try {
            new Invoice(6000);
        } catch (IllegalArgumentException e) {
            System.out.println("rejected: " + e.getMessage());
        }
        System.out.println(new Invoice(900).number());
    }
}

class Invoice {
    private static final long MAX_AMOUNT = 5000;
    private static int nextNumber = 1000;

    private final int number;
    private final long amount;

    Invoice(long amount) {
        // Kiểm tra trước khi tăng bộ đếm, để số hoá đơn không bị nhảy cóc
        if (amount > MAX_AMOUNT) {
            throw new IllegalArgumentException("amount too large: " + amount);
        }
        this.number = nextNumber++;
        this.amount = amount;
    }

    int number() {
        return number;
    }
}
