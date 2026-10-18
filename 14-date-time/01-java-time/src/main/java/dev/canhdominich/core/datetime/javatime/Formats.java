package dev.canhdominich.core.datetime.javatime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

public final class Formats {

    private static final DateTimeFormatter DAY_FIRST = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Formats() {
    }

    public static String formatAndParse() {
        LocalDate d = LocalDate.of(2026, 8, 14);
        String text = d.format(DAY_FIRST);
        LocalDate parsed = LocalDate.parse("25/12/2026", DAY_FIRST);
        return "formatted " + text + ", parsed " + parsed + ", round trip equal " + LocalDate.parse(text, DAY_FIRST).equals(d);
    }

    public static String parseFailure() {
        try {
            LocalDate.parse("2026-12-25", DAY_FIRST);
            return "accepted";
        } catch (DateTimeParseException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }

    public static String strictness() {
        LocalDate smart = LocalDate.parse("31/02/2026", DAY_FIRST);
        String strict;
        try {
            LocalDate.parse("31/02/2026", DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
            strict = "accepted";
        } catch (DateTimeParseException e) {
            strict = e.getMessage();
        }
        return "default parser turns 31/02/2026 into " + smart + ", strict parser says: " + strict;
    }

    public static String weekYearTrap() {
        LocalDate d = LocalDate.of(2025, 12, 29);
        String right = d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US));
        String wrong = d.format(DateTimeFormatter.ofPattern("YYYY-MM-dd", Locale.US));
        return "yyyy gives " + right + ", YYYY gives " + wrong;
    }

    public static String localized() {
        LocalDate d = LocalDate.of(2026, 8, 14);
        return d.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ENGLISH)) + " | " + d.format(DateTimeFormatter.ISO_DATE);
    }
}
