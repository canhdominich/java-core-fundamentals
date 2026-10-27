public class L1 {
    public static void main(String[] args) {
        System.out.println(order(1, 3));
    }

    static int order(int stock, int qty) throws OutOfStockException {
        if (qty > stock) {
            throw new OutOfStockException("short");
        }
        return stock - qty;
    }
}

class OutOfStockException extends Exception {
    OutOfStockException(String message) {
        super(message);
    }
}
