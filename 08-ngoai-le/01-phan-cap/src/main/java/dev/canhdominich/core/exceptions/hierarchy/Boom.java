package dev.canhdominich.core.exceptions.hierarchy;

public class Boom {

    static void level3() {
        throw new IllegalStateException("boom");
    }

    static void level2() {
        level3();
    }

    static void level1() {
        level2();
    }

    public static void main(String[] args) {
        System.out.println("before");
        level1();
        System.out.println("after, never printed");
    }
}
