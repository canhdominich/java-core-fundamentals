package dev.canhdominich.core.iface.modern;

public class Duck implements Walker, Swimmer {

    @Override
    public String move() {
        return Walker.super.move() + " and " + Swimmer.super.move();
    }
}
