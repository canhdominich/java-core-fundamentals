package dev.canhdominich.core.oop.abstraction;

public class Rectangle extends Shape {

    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        super("rectangle");
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("sides must be positive: " + width + " x " + height);
        }
        this.width = width;
        this.height = height;
        constructed.add("Rectangle(" + width + ", " + height + ")");
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public double perimeter() {
        return 2 * (width + height);
    }
}
