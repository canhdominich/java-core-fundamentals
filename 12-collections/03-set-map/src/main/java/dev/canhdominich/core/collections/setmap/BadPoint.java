package dev.canhdominich.core.collections.setmap;

public class BadPoint {

    private final int x;
    private final int y;

    public BadPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BadPoint point && point.x == x && point.y == y;
    }
}
