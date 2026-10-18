package dev.canhdominich.core.iface.basics;

public class Receipt {

    private final Clock clock;

    public Receipt(Clock clock) {
        this.clock = clock;
    }

    public String issue(Payable payable) {
        return "paid " + payable.amount() + " to " + payable.payee() + " at " + clock.nowMillis();
    }
}
