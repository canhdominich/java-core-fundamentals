package dev.canhdominich.core.oop.abstraction;

public class CsvExporter extends Exporter {

    @Override
    protected String header(int count) {
        return "number,value";
    }

    @Override
    protected String line(int number, String row) {
        return number + "," + row.replace(",", ";");
    }
}
