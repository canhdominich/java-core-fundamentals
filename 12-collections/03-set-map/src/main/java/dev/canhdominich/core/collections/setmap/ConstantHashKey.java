package dev.canhdominich.core.collections.setmap;

public final class ConstantHashKey {

    private static int equalsCalls;

    private final int id;

    public ConstantHashKey(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object other) {
        equalsCalls++;
        return other instanceof ConstantHashKey key && key.id == id;
    }

    @Override
    public int hashCode() {
        return 7;
    }

    public static void reset() {
        equalsCalls = 0;
    }

    public static int calls() {
        return equalsCalls;
    }
}
