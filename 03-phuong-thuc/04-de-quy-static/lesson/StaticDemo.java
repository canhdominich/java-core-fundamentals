public class StaticDemo {
    public static void main(String[] args) {
        Order a = new Order(2);
        Order b = new Order(5);
        a.qty = 3;
        System.out.println(a.describe());
        System.out.println(b.describe());
        System.out.println(Order.created); // đọc bảng đếm chung qua tên class
        Order.reset(); // gọi phương thức static qua tên class, không cần đối tượng
        System.out.println(a.describe());
    }
}

class Order {
    static int created; // một bản chung cho cả class
    int qty; // mỗi đối tượng có một bản riêng

    Order(int qty) {
        this.qty = qty;
        created++; // mỗi lần tạo đối tượng đều tăng bảng đếm chung
    }

    String describe() {
        return "qty " + qty + " of " + created + " orders";
    }

    static void reset() {
        created = 0; // static chỉ chạm được thành viên static
    }
}
