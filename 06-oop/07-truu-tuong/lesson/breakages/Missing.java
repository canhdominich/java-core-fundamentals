abstract class Discount {
    abstract long apply(long price);

    abstract String name();

    final String line(long price) {
        return name() + ": " + apply(price);
    }
}


class Half extends Discount {
    @Override
    String name() {
        return "half";
    }
}
