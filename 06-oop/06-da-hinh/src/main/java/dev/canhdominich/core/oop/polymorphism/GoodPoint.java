package dev.canhdominich.core.oop.polymorphism;

public class GoodPoint {

    private final int x;
    private final int y;

    public GoodPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GoodPoint point)) {
            return false;
        }
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
