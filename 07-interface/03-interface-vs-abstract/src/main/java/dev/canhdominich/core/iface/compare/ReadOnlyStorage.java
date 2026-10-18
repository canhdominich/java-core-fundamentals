package dev.canhdominich.core.iface.compare;

public class ReadOnlyStorage implements Storage {

    private final Storage inner;

    public ReadOnlyStorage(Storage inner) {
        this.inner = inner;
    }

    @Override
    public void put(String key, String value) {
        throw new UnsupportedOperationException("storage is read-only");
    }

    @Override
    public String get(String key) {
        return inner.get(key);
    }

    @Override
    public int size() {
        return inner.size();
    }
}
