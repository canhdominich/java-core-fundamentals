public class StockDemo {
    public static void main(String[] args) {
        try {
            System.out.println(order(5, 2));
            System.out.println(order(1, 3));
        } catch (OutOfStockException e) {
            System.out.println("caught: " + e.getMessage());
        }
    }

    // throws: khai báo cho nơi gọi biết phương thức này có thể ném OutOfStockException
    static int order(int stock, int qty) throws OutOfStockException {
        if (qty <= 0) {
            // Ngoại lệ unchecked có sẵn, không cần khai báo throws
            throw new IllegalArgumentException("qty must be positive");
        }
        if (qty > stock) {
            // throw: chủ động ném, các dòng sau nó trong phương thức không chạy
            throw new OutOfStockException("need " + qty + " but only " + stock + " left");
        }
        return stock - qty;
    }
}

// Ngoại lệ riêng của quán: extends Exception nên là checked
class OutOfStockException extends Exception {
    OutOfStockException(String message) {
        super(message);
    }
}
