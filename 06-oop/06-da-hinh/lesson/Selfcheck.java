import java.util.List;

public class Selfcheck {
    public static void main(String[] args) {
        Discount d = new PercentDiscount(20);
        System.out.println(d.apply(5000) + " " + d.name());
        long price = 5000;
        for (Discount rule : List.of(new PercentDiscount(20), new FixedDiscount(1000))) {
            price = rule.apply(price);
        }
        System.out.println(price);
        Discount h = new HalfDiscount();
        System.out.println(h.apply(100));
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
        return Math.max(0, price - amount);
    }
}

class HalfDiscount extends Discount {
    long apply(int price) {
        return price / 2;
    }
}
