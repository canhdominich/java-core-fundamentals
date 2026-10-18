package dev.canhdominich.core.exceptions.handling;

import java.util.ArrayList;
import java.util.List;

public final class Trace {

    private static final List<String> steps = new ArrayList<>();

    private Trace() {
    }

    public static void log(String step) {
        steps.add(step);
    }

    public static void clear() {
        steps.clear();
    }

    public static List<String> steps() {
        return new ArrayList<>(steps);
    }
}
