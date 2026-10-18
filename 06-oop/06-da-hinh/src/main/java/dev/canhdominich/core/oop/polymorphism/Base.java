package dev.canhdominich.core.oop.polymorphism;

public class Base {

    public static String who() {
        return "Base.who";
    }

    private String secret() {
        return "Base.secret";
    }

    public String reveal() {
        return secret();
    }

    public Base create() {
        return new Base();
    }

    public String kind() {
        return "Base";
    }
}
