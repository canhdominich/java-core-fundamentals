package dev.canhdominich.core.io;

import java.util.Locale;

public class FormatTable {

    static void row(String format, Object value) {
        System.out.printf("%-12s -> [" + format + "]%n", format, value);
    }

    public static void main(String[] args) {
        row("%d", 42);
        row("%5d", 42);
        row("%-5d", 42);
        row("%05d", 42);
        row("%+d", 42);
        row("%,d", 1234567);
        row("%x", 255);
        row("%o", 8);
        row("%b", true);
        row("%c", 'J');
        row("%s", "Java");
        row("%10s", "Java");
        row("%-10s", "Java");
        row("%.2f", 3.14159);
        row("%8.3f", 3.14159);
        row("%e", 12345.678);
        row("%,.2f", 1234567.891);
        row("%%", null);
        double amount = 1234567.891;
        System.out.println("Locale.US      " + String.format(Locale.US, "%,.2f", amount));
        System.out.println("Locale.GERMANY " + String.format(Locale.GERMANY, "%,.2f", amount));
        System.out.println("vi-VN          " + String.format(Locale.forLanguageTag("vi-VN"), "%,.2f", amount));
        System.out.println("Locale.ROOT    " + String.format(Locale.ROOT, "%.2f", amount));
        System.out.println("default locale " + Locale.getDefault() + " -> " + String.format("%,.2f", amount));
        try {
            System.out.printf("%d%n", 3.5);
        } catch (java.util.IllegalFormatConversionException e) {
            System.out.println("printf(\"%d\", 3.5) -> " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        try {
            System.out.printf("%s %s%n", "only one");
        } catch (java.util.MissingFormatArgumentException e) {
            System.out.println("missing argument -> " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
