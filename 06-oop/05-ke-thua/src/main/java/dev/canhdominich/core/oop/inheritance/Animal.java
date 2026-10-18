package dev.canhdominich.core.oop.inheritance;

public class Animal {

    private final String name;
    protected int energy = 10;
    public String label = "animal";

    public Animal(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public String sound() {
        return "...";
    }

    public String describe() {
        return name + " says " + sound();
    }

    public void eat() {
        energy += 5;
    }

    public int energy() {
        return energy;
    }
}
