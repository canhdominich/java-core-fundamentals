import java.util.List;

public class Threshold {
    public static void main(String[] args) {
        List<Discount> rules = List.of(new PercentDiscount(10), new ThresholdDiscount(50000, 20000));
        System.out.println(total(100000, rules));
        System.out.println(total(3000, rules));
    }

    static long total(long price, List<Discount> rules) {
        for (Discount rule : rules) {
            price = rule.apply(price);
        }
        return price;
    }
}

class Discount {
    long apply(long price) {
        return price;
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
}

class ThresholdDiscount extends Discount {
    private final long minimum;
    private final long amount;

    ThresholdDiscount(long minimum, long amount) {
        this.minimum = minimum;
        this.amount = amount;
    }

    @Override
    long apply(long price) {
        if (price >= minimum) {
            return price - amount;
        }
        return price;
    }
}
