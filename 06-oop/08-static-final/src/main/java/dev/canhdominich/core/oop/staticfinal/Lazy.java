package dev.canhdominich.core.oop.staticfinal;

public class Lazy {

    public static final int COMPILE_TIME_CONSTANT = 42;
    public static final int COMPUTED_AT_RUN_TIME = Trace.log("field initializer runs") + 1000;

    static {
        Trace.log("static block runs");
    }

    public Lazy() {
        Trace.log("constructor runs");
    }

    public static void helper() {
        Trace.log("helper runs");
    }
}
