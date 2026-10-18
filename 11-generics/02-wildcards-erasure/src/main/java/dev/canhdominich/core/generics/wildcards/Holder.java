package dev.canhdominich.core.generics.wildcards;

public class Holder<T extends Number> {

    private T value;

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
