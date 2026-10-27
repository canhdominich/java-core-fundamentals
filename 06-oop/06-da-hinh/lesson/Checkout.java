import java.util.List;

public class Checkout {
    public static void main(String[] args) {
        // Danh sách kiểu cha chứa được mọi lớp con
        List<Discount> rules = List.of(new Discount(), new PercentDiscount(10), new PercentDiscount(50));
        long price = 100000;
        for (Discount rule : rules) {
            // Cùng một dòng này chạy apply của lớp nào là do đối tượng thật quyết định
            price = rule.apply(price);
            System.out.println(rule.name() + ": " + price);
        }
    }
}

class Discount {
    long apply(long price) {
        return price;
    }

    String name() {
        return "none";
    }
}

class PercentDiscount extends Discount {
    // Mỗi đối tượng giữ phần trăm riêng
    private final int percent;

    PercentDiscount(int percent) {
        this.percent = percent;
    }

    @Override
    long apply(long price) {
        // price * percent / 100 là số tiền được giảm
        return price - price * percent / 100;
    }

    @Override
    String name() {
        return percent + " percent";
    }
}
