public class L3 {
    public static void main(String[] args) throws Exception {
        throw new PriceException("negative price");
    }
}

class PriceException extends Exception {
}
