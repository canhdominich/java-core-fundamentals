package dev.canhdominich.core.collections.setmap;

public class GoodPoint {

    private final int x;
    private final int y;

    public GoodPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof GoodPoint point && point.x == x && point.y == y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
