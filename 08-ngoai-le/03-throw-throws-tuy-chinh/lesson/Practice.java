public class Practice {
    public static void main(String[] args) {
        try {
            System.out.println("step 2: " + priceOf(30));
            System.out.println("step 2: " + priceOf(-5));
        } catch (PriceException e) {
            System.out.println("step 3: " + e.getMessage());
        }
        try {
            readPrice("abc");
        } catch (PriceException e) {
            System.out.println("step 4: " + e.getCause());
        }
    }

    static int priceOf(int price) throws PriceException {
        if (price < 0) {
            throw new PriceException("negative price: " + price);
        }
        return price;
    }

    static int readPrice(String text) throws PriceException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            // Truyền e làm cause để không mất lỗi gốc
            throw new PriceException("bad price: " + text, e);
        }
    }
}

class PriceException extends Exception {
    PriceException(String message) {
        super(message);
    }

    PriceException(String message, Throwable cause) {
        super(message, cause);
    }
}
