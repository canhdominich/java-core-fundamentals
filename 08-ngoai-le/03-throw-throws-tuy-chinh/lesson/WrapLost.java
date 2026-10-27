public class WrapLost {
    public static void main(String[] args) {
        try {
            describe("abc");
        } catch (OrderException e) {
            System.out.println(e.getMessage() + " / cause: " + e.getCause());
        }
    }

    static void describe(String id) throws OrderException {
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            // Quên truyền e: nguyên nhân gốc bị mất
            throw new OrderException("cannot read order " + id);
        }
    }
}

class OrderException extends Exception {
    OrderException(String message) {
        super(message);
    }
}
