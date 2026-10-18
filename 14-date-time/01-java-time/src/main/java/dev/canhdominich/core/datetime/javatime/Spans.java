package dev.canhdominich.core.datetime.javatime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

public final class Spans {

    private Spans() {
    }

    public static String periodVersusDuration() {
        LocalDate a = LocalDate.of(2026, 1, 1);
        LocalDate b = LocalDate.of(2026, 12, 31);
        Period period = Period.between(a, b);
        long days = ChronoUnit.DAYS.between(a, b);
        Duration duration = Duration.between(LocalTime.of(9, 0), LocalTime.of(11, 30));
        return "Period " + period + " (" + period.getMonths() + " months " + period.getDays() + " days), total days " + days + ", Duration " + duration + " = " + duration.toMinutes() + " minutes";
    }

    public static String durationOnDates() {
        try {
            Duration.between(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 2));
            return "accepted";
        } catch (UnsupportedTemporalTypeException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }

    public static String ageCalculation(LocalDate birthday, LocalDate today) {
        Period age = Period.between(birthday, today);
        return age.getYears() + " years " + age.getMonths() + " months " + age.getDays() + " days";
    }

    public static String unitsBetween() {
        LocalDate a = LocalDate.of(2026, 1, 31);
        LocalDate b = LocalDate.of(2026, 3, 1);
        return "Period " + Period.between(a, b) + ", months " + ChronoUnit.MONTHS.between(a, b) + ", days " + ChronoUnit.DAYS.between(a, b) + ", weeks " + ChronoUnit.WEEKS.between(a, b);
    }
}
