package dev.canhdominich.core.oop.polymorphism;

public class PercentDiscount extends Discount {

    private final int percent;

    public PercentDiscount(int percent) {
        this.percent = percent;
    }

    @Override
    public long apply(long price) {
        return price - price * percent / 100;
    }

    @Override
    public String name() {
        return percent + " percent";
    }
}
