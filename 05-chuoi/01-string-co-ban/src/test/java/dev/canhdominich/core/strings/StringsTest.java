package dev.canhdominich.core.strings;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StringsTest {

    @Test
    void stringMethodsReturnNewStringsAndNeverChangeTheOriginal() {
        assertThat(StringBasics.immutability()).isEqualTo("name java, toUpperCase JAVA, replace jovo, name again java");
    }

    @Test
    void outOfRangeIndexesThrowStringIndexOutOfBoundsException() {
        assertThat(StringBasics.errors()).isEqualTo("charAt(5): String index out of range: 5 | substring(2, 9): begin 2, end 9, length 5 | substring(4, 2): begin 4, end 2, length 5");
    }

    @Test
    void substringExcludesTheEndIndexAndIndexOfReturnsMinusOneWhenMissing() {
        assertThat(StringBasics.slicing()).isEqualTo("substring(6) world, substring(0, 5) hello, substring(5, 5) [], indexOf(o) 4, indexOf(o, 5) 7, lastIndexOf(o) 7, indexOf(z) -1, contains(wor) true");
    }

    @Test
    void replaceTakesLiteralTextWhileReplaceAllTakesARegularExpression() {
        assertThat(StringBasics.replacing()).isEqualTo("replace dot a-b-c, replaceAll dot -----, replaceAll escaped a-b-c, replaceFirst a-b.c");
    }

    @Test
    void stripUnderstandsUnicodeWhitespaceButTrimDoesNot() {
        assertThat(StringBasics.whitespace()).isEqualTo("trim length 8, strip length 4, isBlank of spaces true, isEmpty of spaces false");
    }

    @Test
    void splitDropsTrailingEmptyPartsUnlessTheLimitIsNegative() {
        assertThat(StringBasics.splitting()).isEqualTo("2 parts for a,b,, | 4 with limit -1 | 1 part for the empty string | 0 parts when splitting on a dot | 3 parts when splitting on an escaped dot | [, a, , b] | [a, b]");
    }

    @Test
    void stringsCanBeBuiltFromArraysRepeatedAndJoined() {
        assertThat(StringBasics.building()).isEqualTo("from char[] java, valueOf(42) 42, repeat ababab, join a-b-c, toCharArray length 5");
    }

    @Test
    void equalsComparesContentAndDoubleEqualsComparesIdentity() {
        assertThat(StringCompare.equality()).isEqualTo("literal == literal true, literal == new String false, equals true, interned == literal true");
    }

    @Test
    void callingEqualsOnTheLiteralIsSafeWhenTheInputMayBeNull() {
        assertThat(StringCompare.caseAndNull(null)).isEqualTo("input.equals throws NullPointerException; \"yes\".equals(input) false; Objects.equals false");
        assertThat(StringCompare.caseAndNull("yes")).isEqualTo("input.equals true, \"yes\".equals true");
    }

    @Test
    void compareToOrdersByCharacterCodesSoUppercaseComesFirst() {
        assertThat(StringCompare.ignoringCase()).isEqualTo("equalsIgnoreCase true, equals false");
        assertThat(StringCompare.ordering()).isEqualTo("apple vs banana -1, banana vs apple 1, apple vs apple 0, apple vs apples -1, Zebra vs apple -7, ignoring case 25");
    }

    @Test
    void constantExpressionsAreFoldedAtCompileTimeButRuntimeConcatenationIsNot() {
        assertThat(StringCompare.compileTimeConstants()).isEqualTo("\"ja\" + \"va\" == \"java\" true, a + b == \"java\" false, final fa + \"va\" == \"java\" true");
    }

    @Test
    void plusEvaluatesLeftToRightSoTheFirstOperandsDecideNumberOrText() {
        assertThat(Concatenation.withNumbers()).isEqualTo("1 + 2 + \"3\" = 33, \"1\" + 2 + 3 = 123, \"1\" + (2 + 3) = 15");
        assertThat(Concatenation.withChars()).isEqualTo("'a' + 'b' = 195, \"\" + 'a' + 'b' = ab, \"x\" + 'a' + 1 = xa1");
    }

    @Test
    void nullBecomesTheFourLettersNullAndDecimalsConcatenateAsText() {
        assertThat(Concatenation.withNull()).isEqualTo("\"value: \" + null = value: null, length 11");
        assertThat(Concatenation.withDecimals()).isEqualTo("\"x\" + 0.1 + 0.2 = x0.10.2, \"x\" + (0.1 + 0.2) = x0.30000000000000004");
    }

    @Test
    void formatBuildsTextFromAPatternAndArguments() {
        assertThat(Concatenation.formatting()).isEqualTo("cart has 3 items costing 19.50 |    42|42   |00042 | 50%");
        assertThat(Concatenation.plusEquals()).isEqualTo("12345");
    }

    @Test
    void theCompilerRejectsArrayStyleAccessAndWrongLiterals() {
        assertThat(Javac.messages("A", "class A { char f(String s) { return s[0]; } }")).containsExactly("line 1: array required, but java.lang.String found");
        assertThat(Javac.messages("A", "class A { String s = 'a'; }")).containsExactly("line 1: incompatible types: char cannot be converted to java.lang.String");
        assertThat(Javac.messages("A", "class A { void f(String s) { s.charAt(0) = 'x'; } }")).containsExactly("line 1: unexpected type");
    }
}
