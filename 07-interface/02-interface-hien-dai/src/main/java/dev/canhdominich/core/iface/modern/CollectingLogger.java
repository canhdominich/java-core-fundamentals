package dev.canhdominich.core.iface.modern;

import java.util.ArrayList;
import java.util.List;

public class CollectingLogger implements Logger {

    private final List<String> lines = new ArrayList<>();

    @Override
    public void log(String line) {
        lines.add(line);
    }

    public List<String> lines() {
        return lines;
    }
}
