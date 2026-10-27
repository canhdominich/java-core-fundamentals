public class Breakages {
    public static void main(String[] args) {
        int[] prices = {45_000, 18_500, 32_000};
        int total = 0;
        try {
            for (int i = 0; i <= prices.length; i++) { // <= cho một vòng thừa
                total += prices[i];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("1: total " + total + ", then " + e.getMessage());
        }
        int runs = 0;
        for (int i = 0; i < 3; i++); { // dấu ; thừa làm thân rỗng
            runs++;
        }
        System.out.println("2: runs " + runs);
        for (int price : prices) {
            price = price * 2;
        }
        System.out.println("3: prices[0] " + prices[0]);
        int stock = 3;
        do {
            stock -= 4;
        } while (stock >= 4);
        System.out.println("4: stock " + stock);
    }
}
