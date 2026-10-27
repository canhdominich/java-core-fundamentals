public class Selfcheck {
    static void reset(Order order) {
        order.qty = 0; // sửa trường qua địa chỉ nên bên ngoài thấy
    }

    static void fresh(Order order) {
        order = new Order(7); // chỉ đổi bản sao địa chỉ
        order.qty = order.qty + 1;
    }

    static int sum(int... values) {
        return values.length; // mảng rỗng khi không có đối số
    }

    public static void main(String[] args) {
        Order a = new Order(3);
        reset(a);
        System.out.println(a.qty);
        Order b = new Order(3);
        fresh(b);
        System.out.println(b.qty);
        System.out.println(sum() + " " + sum(5) + " " + sum(5, 6, 7));
    }
}

class Order {
    int qty;

    Order(int qty) {
        this.qty = qty;
    }
}
