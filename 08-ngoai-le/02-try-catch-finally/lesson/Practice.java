public class Practice {
    public static void main(String[] args) {
        serve("latte");
        serve(null);
    }

    static void serve(String drink) {
        System.out.println("open");
        try {
            System.out.println(drink.length());
        } catch (NullPointerException e) {
            System.out.println("no drink");
        } finally {
            // Đóng quầy dù có lỗi hay không
            System.out.println("close");
        }
    }
}
