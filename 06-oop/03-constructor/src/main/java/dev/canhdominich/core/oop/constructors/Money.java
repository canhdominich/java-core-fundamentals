package dev.canhdominich.core.oop.constructors;

public final class Money {

    public static final Money ZERO = new Money(0);

    private final long amount;

    private Money(long amount) {
        this.amount = amount;
    }

    public static Money of(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must not be negative: " + amount);
        }
        return amount == 0 ? ZERO : new Money(amount);
    }

    public long amount() {
        return amount;
    }
}
