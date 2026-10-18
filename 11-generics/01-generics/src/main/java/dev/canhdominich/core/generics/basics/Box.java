package dev.canhdominich.core.generics.basics;

public class Box<T> {

    private T content;

    public Box(T content) {
        this.content = content;
    }

    public T get() {
        return content;
    }

    public void set(T content) {
        this.content = content;
    }

    public <R> Box<R> map(java.util.function.Function<T, R> function) {
        return new Box<>(function.apply(content));
    }
}
