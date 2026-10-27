public class Trace {
    public static void main(String[] args) {
        try {
            System.out.println(order(5, 2));
            System.out.println(order(3, 3));
            System.out.println(order(1, 3));
            System.out.println(order(9, 1));
        } catch (OutOfStockException e) {
            System.out.println("caught: " + e.getMessage());
        }
    }

    static int order(int stock, int qty) throws OutOfStockException {
        if (qty <= 0) {
            throw new IllegalArgumentException("qty must be positive");
        }
        if (qty > stock) {
            throw new OutOfStockException("need " + qty + " but only " + stock + " left");
        }
        return stock - qty;
    }
}

class OutOfStockException extends Exception {
    OutOfStockException(String message) {
        super(message);
    }
}
