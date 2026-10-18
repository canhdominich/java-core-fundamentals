package dev.canhdominich.core.exceptions.handling;

import java.util.List;

public final class Flow {

    private Flow() {
    }

    public static List<String> run(String input) {
        Trace.clear();
        try {
            Trace.log("try starts");
            int value = Integer.parseInt(input);
            Trace.log("parsed " + value);
            int result = 100 / value;
            Trace.log("result " + result);
        } catch (NumberFormatException e) {
            Trace.log("catch NumberFormatException");
        } catch (ArithmeticException e) {
            Trace.log("catch ArithmeticException");
        } finally {
            Trace.log("finally");
        }
        Trace.log("after the try statement");
        return Trace.steps();
    }

    public static List<String> uncaught() {
        Trace.clear();
        try {
            try {
                Trace.log("inner try");
                throw new IllegalStateException("boom");
            } finally {
                Trace.log("inner finally");
            }
        } catch (IllegalStateException e) {
            Trace.log("outer catch " + e.getMessage());
        }
        return Trace.steps();
    }

    @SuppressWarnings("finally")
    public static String returnInFinally() {
        try {
            return "from try";
        } finally {
            Trace.log("finally ran before the return completed");
        }
    }

    public static int returnValueIsCaptured() {
        int value = 1;
        try {
            return value;
        } finally {
            value = 99;
        }
    }

    @SuppressWarnings("finally")
    public static String finallyOverrides() {
        try {
            throw new IllegalStateException("original");
        } finally {
            return "finally returned, the exception vanished";
        }
    }

    public static int parseOrZero(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static int parseOrReport(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("not a number: '" + text + "'", e);
        }
    }

    public static String multiCatch(String input) {
        try {
            if (input == null) {
                throw new NullPointerException("input");
            }
            return String.valueOf(Integer.parseInt(input));
        } catch (NumberFormatException | NullPointerException e) {
            return "multi-catch caught " + e.getClass().getSimpleName();
        }
    }
}
