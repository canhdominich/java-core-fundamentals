public class StockReport {
    public static void main(String[] args) {
        int[] stock = {5, 8, 0, 3, 0, 12};
        int filled = 0;
        int units = 0;
        for (int s : stock) {
            if (s == 0) {
                continue; // ô trống không tính
            }
            filled++;
            units += s;
        }
        int firstEmpty = -1;
        for (int i = 0; i < stock.length; i++) {
            if (stock[i] == 0) {
                firstEmpty = i;
                break; // thấy ô trống đầu tiên thì dừng
            }
        }
        System.out.println("non-empty slots = " + filled);
        System.out.println("total units = " + units);
        System.out.println("first empty slot = " + firstEmpty);
    }
}
