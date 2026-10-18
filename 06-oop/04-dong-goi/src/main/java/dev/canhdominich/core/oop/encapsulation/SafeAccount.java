package dev.canhdominich.core.oop.encapsulation;

public class SafeAccount {

    private final String owner;
    private long balance;
    private int operations;

    public SafeAccount(String owner, long openingBalance) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("owner must not be blank");
        }
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

    public int operations() {
        return operations;
    }

    public void deposit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("deposit must be positive: " + amount);
        }
        balance += amount;
        operations++;
    }

    public void withdraw(long amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException("cannot withdraw " + amount + " from " + balance);
        }
        balance -= amount;
        operations++;
    }
}
