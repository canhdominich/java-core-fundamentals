package dev.canhdominich.core.hello;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HelloTest {

    @Test
    void greetingUsesTheName() {
        assertThat(Hello.greeting("Lan")).isEqualTo("Hello, Lan!");
    }

    @Test
    void aMissingSemicolonIsReportedAtTheEndOfTheStatement() throws Exception {
        Compile.Result result = Compile.source("Hello.java", BrokenPrograms.MISSING_SEMICOLON);
        assertThat(result.ok()).isFalse();
        assertThat(result.messages().get(0)).isEqualTo("line 3: ';' expected");
    }

    @Test
    void anUnknownNameIsReportedAsCannotFindSymbol() throws Exception {
        assertThat(Compile.source("Hello.java", BrokenPrograms.UNKNOWN_SYMBOL).messages().get(0)).isEqualTo("line 3: cannot find symbol");
    }

    @Test
    void aPublicClassMustLiveInAFileWithTheSameName() throws Exception {
        assertThat(Compile.source("Hello.java", BrokenPrograms.WRONG_FILE_NAME).messages().get(0))
                .isEqualTo("line 1: class Wrong is public, should be declared in a file named Wrong.java");
    }

    @Test
    void aWrongClassNameStopsTheLauncher() throws Exception {
        Compile.Result compiled = Compile.source("Hello.java", BrokenPrograms.EXIT_THREE);
        Run.Outcome outcome = Run.java(compiled.outputDirectory(), "-cp", ".", "Helo");
        assertThat(outcome.exitCode()).isEqualTo(1);
        assertThat(outcome.firstLine()).startsWith("Error: Could not find or load main class Helo");
    }

    @Test
    void theExitCodeComesFromSystemExit() throws Exception {
        Compile.Result compiled = Compile.source("Hello.java", BrokenPrograms.EXIT_THREE);
        Run.Outcome outcome = Run.java(compiled.outputDirectory(), "-cp", ".", "Hello");
        assertThat(outcome.exitCode()).isEqualTo(3);
        assertThat(outcome.firstLine()).isEqualTo("about to exit");
    }

    @Test
    void anExceptionInMainGivesExitCodeOne() throws Exception {
        Compile.Result compiled = Compile.source("Hello.java", BrokenPrograms.THROWS_AT_RUNTIME);
        Run.Outcome outcome = Run.java(compiled.outputDirectory(), "-cp", ".", "Hello");
        assertThat(outcome.exitCode()).isEqualTo(1);
        assertThat(outcome.firstLine()).contains("ArrayIndexOutOfBoundsException");
    }
}
