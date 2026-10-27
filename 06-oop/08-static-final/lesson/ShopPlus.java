public class ShopPlus {
    public static void main(String[] args) {
        Invoice a = new Invoice(700);
        Invoice b = new Invoice(1200);
        System.out.println(a.code() + " " + a.amount());
        System.out.println(b.code() + " " + b.amount());
        System.out.println("total " + Invoice.total());
    }
}

class Invoice {
    private static final String PREFIX = "INV-";
    private static int nextNumber = 1000;
    private static long totalAmount = 0;

    private final int number;
    private final long amount;

    Invoice(long amount) {
        this.number = nextNumber++;
        this.amount = amount;
        totalAmount += amount;
    }

    long amount() {
        return amount;
    }

    String code() {
        return PREFIX + number;
    }

    static long total() {
        return totalAmount;
    }
}
