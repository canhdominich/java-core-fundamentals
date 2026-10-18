package dev.canhdominich.core.iface.basics;

public class SystemClock implements Clock {

    @Override
    public long nowMillis() {
        return System.currentTimeMillis();
    }
}
