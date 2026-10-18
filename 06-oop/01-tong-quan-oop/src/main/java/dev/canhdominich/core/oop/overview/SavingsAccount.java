package dev.canhdominich.core.oop.overview;

public class SavingsAccount extends Account {

    private final int ratePercent;

    public SavingsAccount(String owner, long openingBalance, int ratePercent) {
        super(owner, openingBalance);
        this.ratePercent = ratePercent;
    }

    @Override
    public long monthEnd() {
        long interest = balance() * ratePercent / 100;
        deposit(interest);
        return interest;
    }
}
