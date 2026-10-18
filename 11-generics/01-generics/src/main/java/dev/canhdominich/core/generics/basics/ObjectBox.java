package dev.canhdominich.core.generics.basics;

public class ObjectBox {

    private Object content;

    public ObjectBox(Object content) {
        this.content = content;
    }

    public Object get() {
        return content;
    }
}
