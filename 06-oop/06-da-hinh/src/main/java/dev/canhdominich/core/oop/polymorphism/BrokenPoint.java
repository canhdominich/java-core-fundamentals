package dev.canhdominich.core.oop.polymorphism;

public class BrokenPoint {

    private final int x;
    private final int y;

    public BrokenPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(BrokenPoint other) {
        return other != null && x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
