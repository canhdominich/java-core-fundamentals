package dev.canhdominich.core.oop.staticfinal;

public class Invoice {

    private static int nextNumber = 1000;

    private final int number;
    private final String customer;
    private final long amount;

    public Invoice(String customer, long amount) {
        this.number = nextNumber++;
        this.customer = customer;
        this.amount = amount;
    }

    public int number() {
        return number;
    }

    public String customer() {
        return customer;
    }

    public long amount() {
        return amount;
    }

    public static void resetNumbering(int start) {
        nextNumber = start;
    }
}
