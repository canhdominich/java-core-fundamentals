package dev.canhdominich.core.oop.overview;

public class Account {

    private final String owner;
    private long balance;

    public Account(String owner, long openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("opening balance must not be negative: " + openingBalance);
        }
        this.owner = owner;
        this.balance = openingBalance;
    }

    public String owner() {
        return owner;
    }

    public long balance() {
        return balance;
    }

    public void deposit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("deposit must be positive: " + amount);
        }
        balance += amount;
    }

    public void withdraw(long amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException("cannot withdraw " + amount + " from " + balance);
        }
        balance -= amount;
    }

    public long monthEnd() {
        return 0;
    }
}
