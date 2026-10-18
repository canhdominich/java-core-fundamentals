package dev.canhdominich.core.exceptions.handling;

import java.util.List;

public final class Resources {

    private Resources() {
    }

    public static List<String> orderOfClosing() {
        Trace.clear();
        try (Resource first = new Resource("A", false); Resource second = new Resource("B", false)) {
            first.use(false);
            second.use(false);
        }
        return Trace.steps();
    }

    public static List<String> closedEvenWhenTheBodyFails() {
        Trace.clear();
        try (Resource only = new Resource("A", false)) {
            only.use(true);
        } catch (IllegalStateException e) {
            Trace.log("caught " + e.getMessage());
        }
        return Trace.steps();
    }

    public static String suppressed() {
        try (Resource noisy = new Resource("A", true)) {
            noisy.use(true);
            return "no exception";
        } catch (RuntimeException e) {
            StringBuilder out = new StringBuilder("caught " + e.getClass().getSimpleName() + ": " + e.getMessage());
            for (Throwable hidden : e.getSuppressed()) {
                out.append(" | suppressed ").append(hidden.getClass().getSimpleName()).append(": ").append(hidden.getMessage());
            }
            return out.toString();
        }
    }

    public static String manualFinallySwallowingTheCloseFailure() {
        Resource noisy = new Resource("A", true);
        try {
            noisy.use(true);
            return "no exception";
        } catch (RuntimeException e) {
            return "caught " + e.getMessage();
        } finally {
            try {
                noisy.close();
            } catch (RuntimeException closeFailure) {
                Trace.log("close failure swallowed");
            }
        }
    }

    public static String manualFinallyThrowing() {
        Resource noisy = new Resource("A", true);
        try {
            try {
                noisy.use(true);
                return "no exception";
            } finally {
                noisy.close();
            }
        } catch (RuntimeException e) {
            return "caught " + e.getClass().getSimpleName() + ": " + e.getMessage() + ", suppressed " + e.getSuppressed().length;
        }
    }
}
