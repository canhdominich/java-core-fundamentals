public class Tiered {
    public static void main(String[] args) {
        Discount tiered = new TieredDiscount();
        System.out.println(tiered.line(100000));
        System.out.println(tiered.line(3000));
    }
}

abstract class Discount {
    abstract long apply(long price);

    abstract String name();

    final String line(long price) {
        return name() + ": " + apply(price);
    }
}

class TieredDiscount extends Discount {
    @Override
    long apply(long price) {
        if (price >= 50000) {
            return price - price * 5 / 100;
        }
        return price;
    }

    @Override
    String name() {
        return "tiered";
    }
}
