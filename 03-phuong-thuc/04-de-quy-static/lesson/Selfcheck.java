public class Selfcheck {
    static int f(int n) {
        if (n <= 0) {
            return 0;
        }
        return n + f(n - 2); // mỗi lần đệ quy giảm n đi 2
    }

    public static void main(String[] args) {
        System.out.println(f(5) + " " + f(6));
        Order a = new Order(2);
        System.out.println(Order.created);
        Order b = new Order(5);
        System.out.println(Order.created);
        Order.reset(); // đặt lại bảng đếm chung về 0
        System.out.println(Order.created);
        Order c = new Order(1);
        System.out.println(Order.created + " " + a.qty + " " + c.qty);
    }
}

class Order {
    static int created;
    int qty;

    Order(int qty) {
        this.qty = qty;
        created++;
    }

    static void reset() {
        created = 0;
    }
}
