public class ForEach {
    public static void main(String[] args) {
        int[] prices = {45_000, 18_500, 32_000};
        int sum = 0;
        for (int price : prices) { // price là bản sao của từng phần tử
            sum += price;
        }
        for (int price : prices) {
            price = price * 2; // chỉ đổi bản sao, mảng không đổi
        }
        System.out.println("sum = " + sum + ", prices[0] = " + prices[0]);
        for (int i = 0; i < prices.length; i++) {
            prices[i] = prices[i] * 2; // muốn đổi phần tử thì phải dùng chỉ số
        }
        System.out.println("after index loop, prices[0] = " + prices[0]);
    }
}
