package dev.canhdominich.core.oop.inheritance;

public class Dog extends Animal {

    public String label = "dog";

    public Dog(String name) {
        super(name);
    }

    @Override
    public String sound() {
        return "woof";
    }

    public String fetch() {
        energy -= 3;
        return name() + " fetches";
    }

    @Override
    public String describe() {
        return super.describe() + " and wags its tail";
    }
}
