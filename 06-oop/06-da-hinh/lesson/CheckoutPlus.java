import java.util.List;

public class CheckoutPlus {
    static long total(long price, List<Discount> rules) {
        for (Discount rule : rules) {
            price = rule.apply(price);
        }
        return price;
    }

    public static void main(String[] args) {
        List<Discount> rules = List.of(new PercentDiscount(10), new FixedDiscount(5000));
        System.out.println("total: " + total(100000, rules));
        System.out.println("total: " + total(3000, List.of(new FixedDiscount(5000))));
        System.out.println(new FixedDiscount(5000).name());
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
    private final int percent;

    PercentDiscount(int percent) {
        this.percent = percent;
    }

    @Override
    long apply(long price) {
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
