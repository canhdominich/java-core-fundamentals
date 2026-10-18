package dev.canhdominich.core.iface.basics;

public interface Payable {

    int MAX_AMOUNT = 1_000_000;

    String payee();

    long amount();

    default boolean isWithinLimit() {
        return amount() <= MAX_AMOUNT;
    }
}
