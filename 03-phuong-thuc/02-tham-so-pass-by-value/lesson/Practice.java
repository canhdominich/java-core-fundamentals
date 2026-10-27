public class Practice {
    static void swapQty(Order a, Order b) {
        int temp = a.qty;
        a.qty = b.qty; // hoán đổi nội dung chứ không hoán đổi tham số
        b.qty = temp;
    }

    static String addBang(String text) {
        return text + "!";
    }

    static int total(String label, int... prices) {
        int sum = 0;
        for (int price : prices) { // prices là mảng, rỗng khi không có đối số
            sum += price;
        }
        System.out.println(label + ": " + prices.length + " items, " + sum);
        return sum;
    }

    static void discount(Order order, int percent) {
        order.qty = order.qty - order.qty * percent / 100;
    }

    static void swapInts(int[] pair) {
        int temp = pair[0];
        pair[0] = pair[1];
        pair[1] = temp;
    }

    public static void main(String[] args) {
        Order x = new Order(1);
        Order y = new Order(2);
        swapQty(x, y);
        System.out.println(x.qty + " " + y.qty);
        String text = "hi";
        text = addBang(text);
        System.out.println(text);
        total("empty");
        total("combo", 45000, 30000, 38000);
        Order z = new Order(200);
        discount(z, 25);
        System.out.println(z.qty);
        int[] pair = {10, 20};
        swapInts(pair);
        System.out.println(pair[0] + " " + pair[1]);
    }
}

class Order {
    int qty;

    Order(int qty) {
        this.qty = qty;
    }
}
