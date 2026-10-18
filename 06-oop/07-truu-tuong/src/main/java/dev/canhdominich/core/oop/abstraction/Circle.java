package dev.canhdominich.core.oop.abstraction;

public class Circle extends Shape {

    private final double radius;

    public Circle(double radius) {
        super("circle");
        if (radius <= 0) {
            throw new IllegalArgumentException("radius must be positive: " + radius);
        }
        this.radius = radius;
        constructed.add("Circle(" + radius + ")");
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
}
