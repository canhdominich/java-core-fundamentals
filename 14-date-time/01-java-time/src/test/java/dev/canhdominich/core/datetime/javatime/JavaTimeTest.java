package dev.canhdominich.core.datetime.javatime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Test;

class JavaTimeTest {

    @Test
    void localDateIsImmutable() {
        LocalDate d = LocalDate.of(2026, 8, 14);
        d.plusDays(1);
        assertThat(d).isEqualTo(LocalDate.of(2026, 8, 14));
        assertThat(Calendars.immutability()).contains("same object: false");
    }

    @Test
    void impossibleDateIsRejected() {
        assertThatThrownBy(() -> LocalDate.of(2026, 2, 30)).isInstanceOf(DateTimeException.class).hasMessage("Invalid date 'FEBRUARY 30'");
    }

    @Test
    void monthEndClampsAndDoesNotRecover() {
        LocalDate jan31 = LocalDate.of(2026, 1, 31);
        assertThat(jan31.plusMonths(1)).isEqualTo(LocalDate.of(2026, 2, 28));
        assertThat(jan31.plusMonths(1).plusMonths(1)).isEqualTo(LocalDate.of(2026, 3, 28));
        assertThat(jan31.plusMonths(2)).isEqualTo(LocalDate.of(2026, 3, 31));
    }

    @Test
    void periodAndDurationMeasureDifferentThings() {
        assertThat(Period.between(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 12, 31)).toString()).isEqualTo("P11M30D");
        assertThat(Spans.periodVersusDuration()).contains("total days 364").contains("150 minutes");
        assertThat(Spans.durationOnDates()).startsWith("UnsupportedTemporalTypeException");
    }

    @Test
    void ageIsCountedInYearsMonthsDays() {
        assertThat(Spans.ageCalculation(LocalDate.of(1997, 3, 20), LocalDate.of(2026, 8, 14))).isEqualTo("29 years 4 months 25 days");
    }

    @Test
    void parsingFollowsThePatternStrictlyInShapeButLenientlyInDays() {
        assertThatThrownBy(() -> LocalDate.parse("2026-12-25", java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))).isInstanceOf(DateTimeParseException.class);
        assertThat(Formats.strictness()).contains("2026-02-28").contains("Invalid date 'FEBRUARY 31'");
        assertThat(Formats.weekYearTrap()).isEqualTo("yyyy gives 2025-12-29, YYYY gives 2026-12-29");
    }

    @Test
    void zonesChangeTheLocalTimeButNotTheInstant() {
        assertThat(Zones.sameInstantTwoZones()).contains("Ho Chi Minh 2026-08-14T09:30 offset +07:00").contains("New York 2026-08-13T22:30 offset -04:00").contains("same instant true");
        assertThat(Zones.vietnamHasNoDst()).isEqualTo("January offset +07:00, July offset +07:00");
        assertThat(Zones.localTimeLosesTheZone()).contains("apart by 7 hours");
    }

    @Test
    void daylightSavingBreaksTheLinkBetweenDayAndTwentyFourHours() {
        assertThat(Zones.springForwardGap()).isEqualTo("asked for 2026-03-08T02:30, got 2026-03-08T03:30 offset -04:00");
        assertThat(Zones.fallBackOverlap()).contains("first -04:00, second -05:00").contains("60 minutes");
        assertThat(Zones.oneDayVersusTwentyFourHours()).isEqualTo("plus 1 day 2026-03-08T12:00, plus 24 hours 2026-03-08T13:00");
    }

    @Test
    void fixedClockMakesTimeLogicTestable() {
        LocalDate deadline = LocalDate.of(2026, 8, 14);
        assertThat(Zones.isExpired(deadline, Clock.fixed(Instant.parse("2026-08-14T23:59:00Z"), ZoneOffset.UTC))).isFalse();
        assertThat(Zones.isExpired(deadline, Clock.fixed(Instant.parse("2026-08-15T00:01:00Z"), ZoneOffset.UTC))).isTrue();
        assertThat(Duration.between(Instant.parse("2026-08-14T00:00:00Z"), Instant.parse("2026-08-15T00:00:00Z")).toHours()).isEqualTo(24);
    }

    @Test
    void legacyTypesBehaveBadly() {
        assertThat(Legacy.dateIsMutable()).contains("86400000 ms").contains("same object true");
        assertThat(Legacy.calendarMonthIsZeroBased()).isEqualTo("January in Calendar.MONTH is 0, in LocalDate it is 1");
        assertThat(Legacy.calendarRollsOver()).isEqualTo("Calendar accepts Feb 30 and shows month 3 day 2");
        assertThat(Legacy.bridge()).contains("back to Date equal true");
    }
}
