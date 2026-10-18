package dev.canhdominich.core.collections.overview;

public final class CountedKey {

    private static int equalsCalls;

    private final int id;

    public CountedKey(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object other) {
        equalsCalls++;
        return other instanceof CountedKey key && key.id == id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    public static void resetCounter() {
        equalsCalls = 0;
    }

    public static int equalsCalls() {
        return equalsCalls;
    }
}
