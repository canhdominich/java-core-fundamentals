package dev.canhdominich.core.datetime.javatime;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public final class Zones {

    private static final ZoneId HCM = ZoneId.of("Asia/Ho_Chi_Minh");
    private static final ZoneId NEW_YORK = ZoneId.of("America/New_York");

    private Zones() {
    }

    public static String sameInstantTwoZones() {
        Instant instant = Instant.parse("2026-08-14T02:30:00Z");
        ZonedDateTime hcm = instant.atZone(HCM);
        ZonedDateTime ny = instant.atZone(NEW_YORK);
        return "instant " + instant + ", Ho Chi Minh " + hcm.toLocalDateTime() + " offset " + hcm.getOffset() + ", New York " + ny.toLocalDateTime() + " offset " + ny.getOffset() + ", same instant " + hcm.toInstant().equals(ny.toInstant());
    }

    public static String springForwardGap() {
        LocalDateTime missing = LocalDateTime.of(2026, 3, 8, 2, 30);
        ZonedDateTime z = ZonedDateTime.of(missing, NEW_YORK);
        return "asked for " + missing + ", got " + z.toLocalDateTime() + " offset " + z.getOffset();
    }

    public static String fallBackOverlap() {
        LocalDateTime ambiguous = LocalDateTime.of(2026, 11, 1, 1, 30);
        ZonedDateTime first = ZonedDateTime.of(ambiguous, NEW_YORK);
        ZonedDateTime second = first.withLaterOffsetAtOverlap();
        return "first " + first.getOffset() + ", second " + second.getOffset() + ", gap between them " + Duration.between(first, second).toMinutes() + " minutes";
    }

    public static String oneDayVersusTwentyFourHours() {
        ZonedDateTime start = ZonedDateTime.of(2026, 3, 7, 12, 0, 0, 0, NEW_YORK);
        ZonedDateTime plusDay = start.plus(Period.ofDays(1));
        ZonedDateTime plusHours = start.plus(Duration.ofHours(24));
        return "plus 1 day " + plusDay.toLocalDateTime() + ", plus 24 hours " + plusHours.toLocalDateTime();
    }

    public static String vietnamHasNoDst() {
        ZonedDateTime winter = ZonedDateTime.of(2026, 1, 15, 12, 0, 0, 0, HCM);
        ZonedDateTime summer = ZonedDateTime.of(2026, 7, 15, 12, 0, 0, 0, HCM);
        return "January offset " + winter.getOffset() + ", July offset " + summer.getOffset();
    }

    public static String localTimeLosesTheZone() {
        LocalDateTime local = LocalDateTime.of(2026, 8, 14, 9, 30);
        Instant fromHcm = local.atZone(HCM).toInstant();
        Instant fromUtc = local.toInstant(ZoneOffset.UTC);
        return "same local time 09:30 is " + fromHcm + " in Ho Chi Minh and " + fromUtc + " in UTC, apart by " + Duration.between(fromHcm, fromUtc).toHours() + " hours";
    }

    public static boolean isExpired(LocalDate deadline, Clock clock) {
        return LocalDate.now(clock).isAfter(deadline);
    }

    public static String clockInjection() {
        LocalDate deadline = LocalDate.of(2026, 8, 14);
        Clock before = Clock.fixed(Instant.parse("2026-08-13T10:00:00Z"), ZoneOffset.UTC);
        Clock on = Clock.fixed(Instant.parse("2026-08-14T23:59:00Z"), ZoneOffset.UTC);
        Clock after = Clock.fixed(Instant.parse("2026-08-15T00:01:00Z"), ZoneOffset.UTC);
        return "deadline " + deadline + ": on Aug 13 " + isExpired(deadline, before) + ", on Aug 14 " + isExpired(deadline, on) + ", on Aug 15 " + isExpired(deadline, after);
    }
}
