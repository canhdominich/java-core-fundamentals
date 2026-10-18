package dev.canhdominich.core.regex;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RegexTest {

    @Test
    void matchesNeedsTheWholeStringWhileFindLooksAnywhere() {
        assertThat(Basics.threeQuestions()).isEqualTo("matches false, lookingAt false, find true at 6 giving 42");
    }

    @Test
    void characterClassesSelectDifferentCharacters() {
        assertThat(Basics.characterClasses()).isEqualTo(
                "digits [1, 9], word characters [Ab1, _x, 9], letters only [Ab, x], not a space [Ab1, _x-9, é!]");
    }

    @Test
    void anchorsAndWordBoundariesRestrictWhereAMatchCanStart() {
        assertThat(Basics.anchors()).isEqualTo(
                "contains cat true, String.matches needs the whole string: false, word boundary finds [cat, cat], starts-with true");
    }

    @Test
    void aGreedyQuantifierTakesAsMuchAsPossibleAndALazyOneAsLittle() {
        assertThat(Basics.greedyVersusLazy()).isEqualTo(
                "greedy [<b>bold</b> and <i>italic</i>], lazy [<b>, </b>, <i>, </i>]");
    }

    @Test
    void quantifiersControlHowManyRepetitionsAreAccepted() {
        assertThat(Basics.quantifiers()).isEqualTo(
                "a? [a, ab, ab], a* [a, ab, abb], a+ [ab, abb], a{2} [aa, aa], a{2,3} [aa, aaa]");
    }

    @Test
    void flagsChangeHowThePatternReadsTheText() {
        assertThat(Basics.alternationAndFlags()).isEqualTo(
                "case-insensitive finds 0 without the flag, with the flag 2, multiline line starts [first, second]");
    }

    @Test
    void namedAndNumberedGroupsExtractPartsOfAMatch() {
        assertThat(Groups.parseDate("2026-10-18")).isEqualTo("year 2026, month 10, day 18, whole 2026-10-18, group count 3");
        assertThat(Groups.parseDate("18/10/2026")).isEqualTo("not a date: 18/10/2026");
        assertThat(Groups.numberedGroups()).isEqualTo("group 0 an@example.com, group 1 an, group 2 example, span 9 to 23");
        assertThat(Groups.optionalGroup()).isEqualTo("optional group is null when absent");
    }

    @Test
    void replacementTextCanReuseGroups() {
        assertThat(Groups.replacing()).isEqualTo(
                "swap An Nguyen Van, mask digits card #### ####, collapse spaces a b c, named 18/10/2026");
        assertThat(Groups.splitting()).isEqualTo(
                "split on commas with spaces [a, b, c, d], split keeps the lookahead [camel, Case, Word]");
    }

    @Test
    void validationPatternsAcceptAndRejectTheExpectedInputs() {
        assertThat(Validation.phones()).isEqualTo("0912345678 true, +84912345678 true, 091234567 false, 09123456789 false");
        assertThat(Validation.emails()).isEqualTo(
                "an@example.com true, a.b+c@mail.co.uk true, an@ false, no-at.example.com false, a@b..c false");
        assertThat(Validation.passwords()).isEqualTo("Secret123 true, secret123 false, Sec1 false");
    }

    @Test
    void badPatternsReportWhatAndWhere() {
        assertThat(RegexDemo.syntaxErrors("(abc")).isEqualTo("Unclosed group near index 4");
        assertThat(RegexDemo.syntaxErrors("*abc")).isEqualTo("Dangling meta character '*' near index 0");
        assertThat(RegexDemo.syntaxErrors("[z-a]")).isEqualTo("Illegal character range near index 3");
        assertThat(RegexDemo.syntaxErrors("abc")).isEqualTo("valid");
        assertThat(Javac.messages("A", "class A { String p = \"\\d+\"; }")).containsExactly("line 1: illegal escape character");
    }

    @Test
    void aSimplePatternStaysFastOnLongInputs() {
        assertThat(Backtracking.millisFor("a+b", 5000)).isLessThan(2000);
    }

    @Test
    void aCompiledPatternGivesTheSameCountAsStringMatches() {
        String[] inputs = {"an@example.com", "bad", "b.c@mail.co.uk", "x@"};
        assertThat(CompileReuse.viaCompiledPattern(inputs, 10)).isEqualTo(CompileReuse.viaStringMatches(inputs, 10)).isEqualTo(20);
    }
}
