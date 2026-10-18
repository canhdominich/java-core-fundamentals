package dev.canhdominich.core.iface.modern;

public interface Logger {

    void log(String line);

    default void info(String message) {
        log(format("INFO", message));
    }

    default void error(String message) {
        log(format("ERROR", message));
    }

    private String format(String level, String message) {
        return "[" + level + "] " + message;
    }

    static Logger silent() {
        return line -> {
        };
    }
}
