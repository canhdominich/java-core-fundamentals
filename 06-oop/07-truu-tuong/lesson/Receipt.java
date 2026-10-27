import java.util.List;

public class Receipt {
    public static void main(String[] args) {
        // Khai báo biến kiểu abstract thì được, chỉ không thể new Discount()
        List<Discount> rules = List.of(new PercentDiscount(10), new PercentDiscount(50));
        for (Discount rule : rules) {
            System.out.println(rule.line(100000));
        }
    }
}

// abstract: bản mẫu chưa hoàn chỉnh
abstract class Discount {
    // Chỗ trống: không có thân, mỗi lớp con cụ thể phải tự viết
    abstract long apply(long price);

    abstract String name();

    // Khung dựng sẵn: gọi hai chỗ trống theo thứ tự cố định, con không ghi đè được
    final String line(long price) {
        return name() + ": " + apply(price);
    }
}

class PercentDiscount extends Discount {
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
