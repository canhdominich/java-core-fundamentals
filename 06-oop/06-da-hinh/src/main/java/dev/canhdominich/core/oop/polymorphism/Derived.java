package dev.canhdominich.core.oop.polymorphism;

public class Derived extends Base {

    public static String who() {
        return "Derived.who";
    }

    public String secret() {
        return "Derived.secret";
    }

    @Override
    public Derived create() {
        return new Derived();
    }

    @Override
    public String kind() {
        return "Derived";
    }
}
