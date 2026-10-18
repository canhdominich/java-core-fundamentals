package dev.canhdominich.core.oop.abstraction;

import java.util.ArrayList;
import java.util.List;

public abstract class Exporter {

    public final String export(List<String> rows) {
        if (rows == null || rows.isEmpty()) {
            throw new IllegalArgumentException("nothing to export");
        }
        List<String> parts = new ArrayList<>();
        parts.add(header(rows.size()));
        for (int i = 0; i < rows.size(); i++) {
            parts.add(line(i + 1, rows.get(i)));
        }
        parts.add(footer());
        return String.join("\n", parts);
    }

    protected abstract String header(int count);

    protected abstract String line(int number, String row);

    protected String footer() {
        return "";
    }
}
