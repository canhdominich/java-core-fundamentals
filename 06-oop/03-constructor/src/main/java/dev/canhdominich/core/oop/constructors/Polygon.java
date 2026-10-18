package dev.canhdominich.core.oop.constructors;

public class Polygon {

    private final int[] xs;

    public Polygon(int[] xs, boolean copy) {
        this.xs = copy ? xs.clone() : xs;
    }

    public int firstX() {
        return xs[0];
    }
}
