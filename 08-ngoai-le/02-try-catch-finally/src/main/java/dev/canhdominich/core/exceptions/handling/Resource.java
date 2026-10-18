package dev.canhdominich.core.exceptions.handling;

public class Resource implements AutoCloseable {

    private final String name;
    private final boolean failOnClose;

    public Resource(String name, boolean failOnClose) {
        this.name = name;
        this.failOnClose = failOnClose;
        Trace.log("open " + name);
    }

    public void use(boolean fail) {
        Trace.log("use " + name);
        if (fail) {
            throw new IllegalStateException("use failed on " + name);
        }
    }

    @Override
    public void close() {
        Trace.log("close " + name);
        if (failOnClose) {
            throw new IllegalArgumentException("close failed on " + name);
        }
    }
}
