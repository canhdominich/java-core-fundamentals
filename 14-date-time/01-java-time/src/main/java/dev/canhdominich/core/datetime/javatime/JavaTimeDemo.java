package dev.canhdominich.core.datetime.javatime;

import java.time.LocalDate;
import java.util.List;

public final class JavaTimeDemo {

    private JavaTimeDemo() {
    }

    public static void main(String[] args) {
        System.out.println("creating            = " + Calendars.creating());
        System.out.println("invalid date        = " + Calendars.invalidDate());
        System.out.println("immutability        = " + Calendars.immutability());
        System.out.println("month end           = " + Calendars.monthEnd());
        System.out.println("adjusters           = " + Calendars.adjusters());
        System.out.println("period vs duration  = " + Spans.periodVersusDuration());
        System.out.println("duration on dates   = " + Spans.durationOnDates());
        System.out.println("units between      = " + Spans.unitsBetween());
        System.out.println("age                 = " + Spans.ageCalculation(LocalDate.of(1997, 3, 20), LocalDate.of(2026, 8, 14)));
        System.out.println("format and parse    = " + Formats.formatAndParse());
        System.out.println("parse failure       = " + Formats.parseFailure());
        System.out.println("strictness          = " + Formats.strictness());
        System.out.println("week year trap      = " + Formats.weekYearTrap());
        System.out.println("localized           = " + Formats.localized());
        System.out.println("two zones           = " + Zones.sameInstantTwoZones());
        System.out.println("spring forward      = " + Zones.springForwardGap());
        System.out.println("fall back           = " + Zones.fallBackOverlap());
        System.out.println("day vs 24 hours     = " + Zones.oneDayVersusTwentyFourHours());
        System.out.println("no dst in Vietnam   = " + Zones.vietnamHasNoDst());
        System.out.println("local loses zone    = " + Zones.localTimeLosesTheZone());
        System.out.println("clock injection     = " + Zones.clockInjection());
        System.out.println("legacy mutable      = " + Legacy.dateIsMutable());
        System.out.println("legacy month        = " + Legacy.calendarMonthIsZeroBased());
        System.out.println("legacy rollover     = " + Legacy.calendarRollsOver());
        System.out.println("legacy bridge       = " + Legacy.bridge());
        List<String> constructor = Javac.messages("A", "import java.time.LocalDate;\nclass A { LocalDate d = new LocalDate(); }");
        List<String> narrowing = Javac.messages("B", "import java.time.*;\nclass B { LocalDate d = LocalDateTime.now(); }");
        List<String> plusDays = Javac.messages("C", "import java.time.*;\nclass C { LocalDate d = LocalDate.now().plusDays(\"1\"); }");
        System.out.println("compile new         = " + constructor);
        System.out.println("compile mix         = " + narrowing);
        System.out.println("compile argument    = " + plusDays);
    }
}
