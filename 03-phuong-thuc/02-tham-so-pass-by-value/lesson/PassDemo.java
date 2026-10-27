public class PassDemo {
    static void addOne(int n) {
        n = n + 1; // chỉ đổi bản sao của n
    }

    static void bump(Order order) {
        order.qty = order.qty + 1; // đi theo địa chỉ tới đối tượng gốc nên bên ngoài thấy
    }

    static void replace(Order order) {
        order = new Order(); // chỉ chuyển bản sao địa chỉ sang đối tượng mới
    }

    public static void main(String[] args) {
        int n = 5;
        addOne(n);
        System.out.println("n = " + n);
        Order o = new Order();
        bump(o);
        System.out.println("qty after bump = " + o.qty);
        replace(o);
        System.out.println("qty after replace = " + o.qty);
    }
}

class Order {
    int qty = 1; // giá trị mặc định khi tạo đối tượng
}
