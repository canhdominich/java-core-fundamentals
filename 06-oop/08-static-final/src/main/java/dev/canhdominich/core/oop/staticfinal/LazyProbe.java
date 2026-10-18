package dev.canhdominich.core.oop.staticfinal;

public class LazyProbe {

    public static void main(String[] args) {
        Trace.clear();
        int constant = Lazy.COMPILE_TIME_CONSTANT;
        Trace.log("read the compile-time constant " + constant);
        if (args.length > 0 && args[0].equals("computed")) {
            int computed = Lazy.COMPUTED_AT_RUN_TIME;
            Trace.log("read the computed value " + computed);
        } else {
            Lazy.helper();
        }
        Trace.log("second use");
        new Lazy();
        System.out.println(Trace.steps());
    }
}
