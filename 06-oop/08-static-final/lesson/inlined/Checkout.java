public class Checkout {
    public static void main(String[] args) {
        int sold = 30;
        System.out.println("left (constant) = " + (Config.MAX_ITEMS - sold));
        System.out.println("left (final) = " + (Config.LIVE_MAX - sold));
    }
}
