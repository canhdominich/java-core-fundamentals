package dev.canhdominich.core.iface.basics;

public class FixedClock implements Clock {

    private final long millis;

    public FixedClock(long millis) {
        this.millis = millis;
    }

    @Override
    public long nowMillis() {
        return millis;
    }
}
