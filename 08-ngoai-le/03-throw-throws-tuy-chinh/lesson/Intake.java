public class Intake {
    public static void main(String[] args) {
        String[] lines = {"latte,2", "mocha,x", "tea"};
        for (String line : lines) {
            try {
                System.out.println(line + " ok " + parseOrder(line));
            } catch (OrderException e) {
                System.out.println(e.getMessage() + " (cause: " + e.getCause() + ")");
            }
        }
    }

    static int parseOrder(String line) throws OrderException {
        try {
            String[] parts = line.split(",");
            return Integer.parseInt(parts[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // Bọc lỗi gốc vào ngoại lệ của quán, giữ nguyên nhân
            throw new OrderException("cannot read order " + line, e);
        }
    }
}

class OrderException extends Exception {
    OrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
