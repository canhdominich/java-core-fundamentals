package dev.canhdominich.core.oop.abstraction;

public class HtmlExporter extends Exporter {

    @Override
    protected String header(int count) {
        return "<ul> <!-- " + count + " items -->";
    }

    @Override
    protected String line(int number, String row) {
        return "  <li>" + row.replace("<", "&lt;") + "</li>";
    }

    @Override
    protected String footer() {
        return "</ul>";
    }
}
