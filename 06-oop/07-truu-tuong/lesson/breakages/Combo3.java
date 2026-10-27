abstract class Discount {
    abstract long apply(long price);

    abstract String name();

    final String line(long price) {
        return name() + ": " + apply(price);
    }
}


abstract class A {
    abstract final void f();
}
