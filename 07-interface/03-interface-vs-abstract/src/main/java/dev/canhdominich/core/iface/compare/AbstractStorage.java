package dev.canhdominich.core.iface.compare;

public abstract class AbstractStorage implements Storage {

    private int writes;

    @Override
    public final void put(String key, String value) {
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("key must not be blank");
        }
        writes++;
        doPut(key, value);
    }

    protected abstract void doPut(String key, String value);

    public int writes() {
        return writes;
    }
}
