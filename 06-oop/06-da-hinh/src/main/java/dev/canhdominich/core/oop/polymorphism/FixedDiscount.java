package dev.canhdominich.core.oop.polymorphism;

public class FixedDiscount extends Discount {

    private final long amount;

    public FixedDiscount(long amount) {
        this.amount = amount;
    }

    @Override
    public long apply(long price) {
        return Math.max(0, price - amount);
    }

    @Override
    public String name() {
        return amount + " off";
    }
}
