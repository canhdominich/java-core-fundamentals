package dev.canhdominich.core.oop.inheritance;

public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String sound() {
        return "meow";
    }
}
