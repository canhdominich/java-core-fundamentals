package dev.canhdominich.core.iface.modern;

public interface Walker {

    default String move() {
        return "walks";
    }
}
