package dev.canhdominich.core.oop.polymorphism;

public class Discount {

    public long apply(long price) {
        return price;
    }

    public String name() {
        return "none";
    }
}
