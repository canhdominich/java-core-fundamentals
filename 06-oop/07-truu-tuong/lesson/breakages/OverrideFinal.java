abstract class Discount {
    abstract long apply(long price);

    abstract String name();

    final String line(long price) {
        return name() + ": " + apply(price);
    }
}


class Half extends Discount {
    long apply(long price) {
        return price / 2;
    }

    String name() {
        return "half";
    }

    String line(long price) {
        return "x";
    }
}
