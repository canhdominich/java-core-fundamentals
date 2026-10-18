package dev.canhdominich.core.parameters;

public class Box {

    public int value;

    public Box(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Box(" + value + ")";
    }
}
