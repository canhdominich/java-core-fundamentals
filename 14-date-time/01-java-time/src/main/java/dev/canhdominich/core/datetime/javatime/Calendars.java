package dev.canhdominich.core.datetime.javatime;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public final class Calendars {

    private Calendars() {
    }

    public static String creating() {
        LocalDate date = LocalDate.of(2026, 8, 14);
        LocalTime time = LocalTime.of(9, 30);
        LocalDateTime both = LocalDateTime.of(date, time);
        LocalDate parsed = LocalDate.parse("2026-12-25");
        return "date " + date + ", time " + time + ", both " + both + ", parsed " + parsed + ", day of week " + date.getDayOfWeek() + ", leap year " + date.isLeapYear();
    }

    public static String invalidDate() {
        try {
            LocalDate.of(2026, 2, 30);
            return "accepted";
        } catch (DateTimeException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }

    public static String immutability() {
        LocalDate original = LocalDate.of(2026, 8, 14);
        original.plusDays(1);
        LocalDate tomorrow = original.plusDays(1);
        return "original after plusDays without using the result: " + original + ", the returned copy: " + tomorrow + ", same object: " + (tomorrow == original);
    }

    public static String monthEnd() {
        LocalDate jan31 = LocalDate.of(2026, 1, 31);
        LocalDate leapJan31 = LocalDate.of(2024, 1, 31);
        return "Jan 31 plus 1 month = " + jan31.plusMonths(1) + ", leap year 2024 Jan 31 plus 1 month = " + leapJan31.plusMonths(1) + ", Jan 31 plus 1 month plus 1 month = " + jan31.plusMonths(1).plusMonths(1) + ", Jan 31 plus 2 months = " + jan31.plusMonths(2);
    }

    public static String adjusters() {
        LocalDate d = LocalDate.of(2026, 8, 14);
        return "first day of month " + d.with(TemporalAdjusters.firstDayOfMonth()) + ", last day of month " + d.with(TemporalAdjusters.lastDayOfMonth()) + ", next Monday " + d.with(TemporalAdjusters.next(DayOfWeek.MONDAY)) + ", previous or same Friday " + d.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));
    }

    public static boolean isBefore(LocalDate a, LocalDate b) {
        return a.isBefore(b);
    }
}
