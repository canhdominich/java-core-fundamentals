public class Tickets {
    static class Order {
        String drink;

        Order(String drink) {
            this.drink = drink;
        }
    }

    public static void main(String[] args) {
        Order a = new Order("Latte");
        // Chỉ chép phiếu, không tạo đơn mới
        Order b = a;
        b.drink = "Mocha";
        System.out.println(a.drink);
        System.out.println(a == b);
        Order c = new Order("Mocha");
        // So hai phiếu, không so nội dung
        System.out.println(a == c);
    }
}
