package dev.canhdominich.core.iface.modern;

public class PrefixLogger implements Logger {

    private final CollectingLogger target = new CollectingLogger();

    @Override
    public void log(String line) {
        target.log("app: " + line);
    }

    @Override
    public void error(String message) {
        Logger.super.error(message.toUpperCase());
    }

    public java.util.List<String> lines() {
        return target.lines();
    }
}
