package dev.canhdominich.core.oop.constructors;

import java.util.ArrayList;
import java.util.List;

public final class Trace {

    private static final List<String> steps = new ArrayList<>();

    private Trace() {
    }

    public static int log(String step) {
        steps.add(step);
        return steps.size();
    }

    public static void clear() {
        steps.clear();
    }

    public static List<String> steps() {
        return new ArrayList<>(steps);
    }
}
