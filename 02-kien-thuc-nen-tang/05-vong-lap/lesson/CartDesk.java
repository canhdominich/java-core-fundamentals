public class CartDesk {
    public static void main(String[] args) {
        int[] prices = {45_000, 18_500, 32_000};
        int sum = 0;
        for (int i = 0; i < prices.length; i++) {
            sum += prices[i];
        }
        System.out.println("step 1: " + sum);
        int bigOnes = 0;
        for (int price : prices) {
            if (price >= 30_000) {
                bigOnes++;
            }
        }
        System.out.println("step 2: " + bigOnes);
        for (int i = 0; i < prices.length; i++) {
            prices[i] = prices[i] * 2; // đổi phần tử bằng chỉ số
        }
        System.out.println("step 3: " + prices[0]);
        int stock = 10;
        int packs = 0;
        while (stock >= 4) {
            stock -= 4;
            packs++;
        }
        System.out.println("step 4: " + packs + " packs, " + stock + " left");
        int[] shelf = {5, 8, 0, 3, 0};
        int firstEmpty = -1;
        for (int i = 0; i < shelf.length; i++) {
            if (shelf[i] == 0) {
                firstEmpty = i;
                break; // dừng ở ô trống đầu tiên
            }
        }
        System.out.println("step 5: " + firstEmpty);
    }
}
