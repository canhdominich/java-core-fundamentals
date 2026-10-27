public class WrapDemo {
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
            // Truyền e làm tham số thứ hai để giữ nguyên nhân gốc
            throw new OrderException("cannot read order " + id, e);
        }
    }
}

class OrderException extends Exception {
    OrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
