package dev.canhdominich.core.oop.encapsulation;

public class Visibility {

    private int privateLevel = 1;
    int packageLevel = 2;
    protected int protectedLevel = 3;
    public int publicLevel = 4;

    public int viaOwnMethod() {
        return privateLevel;
    }
}
