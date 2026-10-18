package dev.canhdominich.core.exceptions.custom;

public class InsufficientFundsException extends Exception {

    private final long needed;
    private final long available;

    public InsufficientFundsException(long needed, long available) {
        super("need " + needed + " but only " + available + " available");
        this.needed = needed;
        this.available = available;
    }

    public long shortfall() {
        return needed - available;
    }
}
