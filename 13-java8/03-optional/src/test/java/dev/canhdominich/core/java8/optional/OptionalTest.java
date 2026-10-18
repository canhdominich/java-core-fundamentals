package dev.canhdominich.core.java8.optional;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class OptionalTest {

    @Test
    void ofRejectsNullWhileOfNullableAcceptsIt() {
        assertThat(Basics.creating()).isEqualTo(
                "Optional.of(null) throws NullPointerException, ofNullable(null) is Optional.empty, of(\"x\") prints as Optional[x], isPresent true, isEmpty true");
    }

    @Test
    void getOnAnEmptyOptionalThrowsWithAFixedMessage() {
        assertThat(Basics.getOnEmpty()).isEqualTo("get on an empty Optional: NoSuchElementException with message 'No value present'");
    }

    @Test
    void orElseAlwaysBuildsItsArgumentWhileOrElseGetDoesSoOnlyWhenNeeded() {
        assertThat(Basics.eagerVersusLazyDefault()).isEqualTo(
                "orElse on a present value built the default 1 time, orElseGet built it 0 more times, and built it when the value was absent: default (1 time)");
    }

    @Test
    void orElseThrowTurnsAbsenceIntoAnExceptionOfYourChoice() {
        assertThat(Basics.orElseThrow()).isEqualTo("orElseThrow raised: no user called Zed");
    }

    @Test
    void mapFlatMapFilterAndOrChainWithoutNullChecks() {
        assertThat(Basics.chaining()).isEqualTo("nickname ANNIE, filter filtered out, or guest, side effects present:An absent");
    }

    @Test
    void aMissingLinkAnywhereInTheChainGivesTheFallback() {
        assertThat(Basics.nestedLookups()).isEqualTo(
                "An HANOI, Binh (no city) unknown, Chi (no address) unknown, Zed (no user) unknown | with null checks: An HANOI, Chi unknown, null unknown");
    }

    @Test
    void optionalStreamsAndEqualityBehaveLikeValues() {
        assertThat(Basics.optionalInStreams()).isEqualTo("Optional.stream turns empty into nothing: [annie], equality: true and true");
        assertThat(Basics.antiPattern()).startsWith("isPresent then get gives An, map then orElse gives An");
    }

    @Test
    void theCompilerDoesNotLetAnOptionalStandInForItsValue() {
        assertThat(Javac.messages("A", "import java.util.*; class A { String f(Optional<String> o) { return o; } }"))
                .containsExactly("line 1: incompatible types: java.util.Optional<java.lang.String> cannot be converted to java.lang.String");
        assertThat(Javac.messages("A", "import java.util.*; class A { Optional<String> f(Optional<String> o) { Optional<Optional<String>> nested = o.map(s -> Optional.of(s)); return nested; } }"))
                .containsExactly("line 1: incompatible types: java.util.Optional<java.util.Optional<java.lang.String>> cannot be converted to java.util.Optional<java.lang.String>");
    }
}
