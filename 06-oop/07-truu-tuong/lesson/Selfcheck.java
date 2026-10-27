public class Selfcheck {
    public static void main(String[] args) {
        System.out.println(new PercentDiscount(20).line(5000));
        System.out.println(new Noisy().line(1));
    }
}

abstract class Discount {
    abstract long apply(long price);

    abstract String name();

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
        return price - price * percent / 100;
    }

    @Override
    String name() {
        return percent + " percent";
    }
}

class Noisy extends Discount {
    @Override
    long apply(long price) {
        // In ra để thấy thứ tự gọi
        System.out.print("A ");
        return price;
    }

    @Override
    String name() {
        System.out.print("N ");
        return "x";
    }
}
