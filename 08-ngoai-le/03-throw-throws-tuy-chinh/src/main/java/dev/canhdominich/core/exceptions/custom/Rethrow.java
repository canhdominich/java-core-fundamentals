package dev.canhdominich.core.exceptions.custom;

public final class Rethrow {

    private Rethrow() {
    }

    static void lowLevel() {
        throw new IllegalStateException("original failure");
    }

    public static String rethrowTheSameObject() {
        try {
            try {
                lowLevel();
            } catch (IllegalStateException e) {
                throw e;
            }
        } catch (IllegalStateException e) {
            return "rethrown as is: top frame is " + e.getStackTrace()[0].getMethodName();
        }
        return "no exception";
    }

    public static String wrapInANewObject() {
        try {
            try {
                lowLevel();
            } catch (IllegalStateException e) {
                throw new IllegalStateException("wrapped: " + e.getMessage());
            }
        } catch (IllegalStateException e) {
            return "wrapped without a cause: top frame is " + e.getStackTrace()[0].getMethodName() + ", cause " + e.getCause();
        }
        return "no exception";
    }

    public static String wrapWithCause() {
        try {
            try {
                lowLevel();
            } catch (IllegalStateException e) {
                throw new IllegalStateException("wrapped: " + e.getMessage(), e);
            }
        } catch (IllegalStateException e) {
            return "wrapped with a cause: top frame is " + e.getStackTrace()[0].getMethodName() + ", cause frame is "
                    + e.getCause().getStackTrace()[0].getMethodName();
        }
        return "no exception";
    }
}
