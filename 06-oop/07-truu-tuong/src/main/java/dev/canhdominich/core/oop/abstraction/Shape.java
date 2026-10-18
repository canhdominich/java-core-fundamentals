package dev.canhdominich.core.oop.abstraction;

import java.util.ArrayList;
import java.util.List;

public abstract class Shape {

    static final List<String> constructed = new ArrayList<>();

    private final String name;

    protected Shape(String name) {
        this.name = name;
        constructed.add("Shape(" + name + ")");
    }

    public abstract double area();

    public abstract double perimeter();

    public String describe() {
        return String.format("%s: area %.2f, perimeter %.2f", name, area(), perimeter());
    }

    public String name() {
        return name;
    }

    public static List<String> constructedLog() {
        return new ArrayList<>(constructed);
    }

    public static void clearLog() {
        constructed.clear();
    }
}
