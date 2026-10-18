package dev.canhdominich.core.exceptions.custom;

public class Wallet {

    private long balance;

    public Wallet(long balance) {
        this.balance = balance;
    }

    public void spend(long amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("amount must be positive: " + amount);
        }
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        balance -= amount;
    }

    public long balance() {
        return balance;
    }
}
