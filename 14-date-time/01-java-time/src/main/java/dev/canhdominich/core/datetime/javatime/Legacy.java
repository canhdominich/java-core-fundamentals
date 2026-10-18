package dev.canhdominich.core.datetime.javatime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class Legacy {

    private Legacy() {
    }

    public static String dateIsMutable() {
        Date original = new Date(0L);
        Date alias = original;
        alias.setTime(86_400_000L);
        return "original after changing the alias: " + original.getTime() + " ms, same object " + (alias == original);
    }

    public static String calendarMonthIsZeroBased() {
        Calendar c = new GregorianCalendar(2026, Calendar.JANUARY, 15);
        int month = c.get(Calendar.MONTH);
        int monthWithJavaTime = LocalDate.of(2026, 1, 15).getMonthValue();
        return "January in Calendar.MONTH is " + month + ", in LocalDate it is " + monthWithJavaTime;
    }

    public static String calendarRollsOver() {
        Calendar c = new GregorianCalendar(2026, Calendar.FEBRUARY, 30);
        return "Calendar accepts Feb 30 and shows month " + (c.get(Calendar.MONTH) + 1) + " day " + c.get(Calendar.DAY_OF_MONTH);
    }

    public static String bridge() {
        Date legacy = new Date(1_786_000_000_000L);
        Instant instant = legacy.toInstant();
        LocalDate date = instant.atZone(ZoneId.of("UTC")).toLocalDate();
        Date back = Date.from(instant);
        return "Date to Instant " + instant + ", to LocalDate " + date + ", back to Date equal " + back.equals(legacy);
    }
}
