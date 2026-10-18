package dev.canhdominich.core.iface.compare;

public interface Storage {

    void put(String key, String value);

    String get(String key);

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}
