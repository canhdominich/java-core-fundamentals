import java.util.List;

public class ReceiptPlus {
    public static void main(String[] args) {
        // Khai báo biến kiểu abstract thì được, chỉ không thể new Discount()
        List<Discount> rules = List.of(new PercentDiscount(10), new FixedDiscount(5000));
        long saved = 0;
        for (Discount rule : rules) {
            System.out.println(rule.line(100000));
            saved += rule.saved(100000);
        }
        System.out.println("total saved: " + saved);
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

    // Code dựng sẵn cũng dùng được chỗ trống apply
    final long saved(long price) {
        return price - apply(price);
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

class FixedDiscount extends Discount {
    private final long amount;

    FixedDiscount(long amount) {
        this.amount = amount;
    }

    @Override
    long apply(long price) {
        // Math.max chặn kết quả không âm
        return Math.max(0, price - amount);
    }

    @Override
    String name() {
        return amount + " off";
    }
}
