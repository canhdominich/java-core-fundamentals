package dev.canhdominich.core.syntax;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SyntaxTest {

    @Test
    void namesAreCaseSensitive() {
        assertThat(IdentifierRules.classify("age")).isEqualTo("valid identifier");
        assertThat(java.util.stream.Stream.of("age", "Age", "AGE").distinct().count()).isEqualTo(3);
    }

    @Test
    void keywordsAndBadNamesAreRejected() {
        assertThat(IdentifierRules.classify("class")).isEqualTo("keyword or reserved literal");
        assertThat(IdentifierRules.classify("goto")).isEqualTo("keyword or reserved literal");
        assertThat(IdentifierRules.classify("true")).isEqualTo("keyword or reserved literal");
        assertThat(IdentifierRules.classify("2total")).isEqualTo("not an identifier");
        assertThat(IdentifierRules.classify("my-var")).isEqualTo("not an identifier");
    }

    @Test
    void varAndRecordAreNotKeywords() {
        assertThat(IdentifierRules.classify("var")).isEqualTo("valid identifier");
        assertThat(IdentifierRules.classify("record")).isEqualTo("valid identifier");
    }

    @Test
    void unicodeLettersAreAllowedInNames() {
        assertThat(IdentifierRules.classify("số_lượng")).isEqualTo("valid identifier");
    }

    @Test
    void conventionsAreCheckedByKind() {
        assertThat(NamingConventions.follows(NamingConventions.Kind.CLASS, "StudentAccount")).isTrue();
        assertThat(NamingConventions.follows(NamingConventions.Kind.CLASS, "studentAccount")).isFalse();
        assertThat(NamingConventions.follows(NamingConventions.Kind.CONSTANT, "MAX_SIZE")).isTrue();
        assertThat(NamingConventions.follows(NamingConventions.Kind.PACKAGE, "com.company.app")).isTrue();
    }

    @Test
    void breakingAConventionStillCompiles() {
        assertThat(SourceChecks.compiles("class studentAccount {}")).isTrue();
        assertThat(SourceChecks.compiles("class A { int my-var = 1; }")).isFalse();
    }

    @Test
    void commentsDoNotChangeTheBytecodeWithoutDebugInfo() throws Exception {
        String plain = CommentsDemo.fingerprint(CommentsDemo.PLAIN, "-g:none");
        assertThat(CommentsDemo.fingerprint(CommentsDemo.SAME_LINE_COMMENT, "-g:none")).isEqualTo(plain);
        assertThat(CommentsDemo.fingerprint(CommentsDemo.COMMENT_LINES, "-g:none")).isEqualTo(plain);
    }

    @Test
    void commentsMoveLineNumbersWhenDebugInfoIsKept() throws Exception {
        assertThat(CommentsDemo.fingerprint(CommentsDemo.COMMENT_LINES)).isNotEqualTo(CommentsDemo.fingerprint(CommentsDemo.PLAIN));
    }

    @Test
    void anEmptyStatementAfterIfMakesTheBlockAlwaysRun() {
        assertThat(EmptyStatementTrap.label(10)).isEqualTo("adult");
        assertThat(SourceChecks.compile("A", EmptyStatementTrap.TRAP).messages()).isEmpty();
        assertThat(SourceChecks.compile("A", EmptyStatementTrap.TRAP, "-Xlint:empty").messages()).anyMatch(m -> m.contains("empty statement after if"));
    }
}
