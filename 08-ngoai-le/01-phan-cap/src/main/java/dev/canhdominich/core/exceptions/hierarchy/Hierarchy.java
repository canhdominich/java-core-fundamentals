package dev.canhdominich.core.exceptions.hierarchy;

public final class Hierarchy {

    private Hierarchy() {
    }

    public static String chain(Class<?> type) {
        StringBuilder out = new StringBuilder();
        for (Class<?> current = type; current != null; current = current.getSuperclass()) {
            if (out.length() > 0) {
                out.append(" extends ");
            }
            out.append(current.getSimpleName());
        }
        return out.toString();
    }

    public static String kind(Class<? extends Throwable> type) {
        if (RuntimeException.class.isAssignableFrom(type)) {
            return "unchecked (RuntimeException)";
        }
        if (Error.class.isAssignableFrom(type)) {
            return "unchecked (Error)";
        }
        return "checked";
    }
}
