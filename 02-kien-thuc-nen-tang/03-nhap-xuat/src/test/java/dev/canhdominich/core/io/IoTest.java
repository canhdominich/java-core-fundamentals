package dev.canhdominich.core.io;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class IoTest {

    @Test
    void nextIntLeavesTheNewlineForNextLine() {
        assertThat(ScannerTraps.naive("25\nAn Binh\n")).isEqualTo("age=25, name=[]");
        assertThat(ScannerTraps.fixed("25\nAn Binh\n")).isEqualTo("age=25, name=[An Binh]");
        assertThat(ScannerTraps.naive("25 An Binh\n")).isEqualTo("age=25, name=[ An Binh]");
    }

    @Test
    void nextReadsOneTokenAndNextLineTheRest() {
        assertThat(ScannerTraps.tokenVersusLine("An Binh\n")).isEqualTo("next()=[An], nextLine()=[ Binh]");
    }

    @Test
    void aBadTokenStaysInTheBufferAfterTheException() {
        assertThat(ScannerTraps.mismatch("abc 5")).isEqualTo("InputMismatchException: null, token still there: [abc]");
    }

    @Test
    void hasNextIntStopsAtTheFirstNonNumber() {
        assertThat(ScannerTraps.sumAll("1 2 3 x 4")).isEqualTo(6);
    }

    @Test
    void safeInputAsksAgainUntilTheAnswerIsValid() {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        int value = SafeInput.readInt(new Scanner("abc\n200\n 30 \n"), new PrintStream(bytes), "age: ", 0, 120);
        assertThat(value).isEqualTo(30);
        assertThat(bytes.toString()).contains("not a whole number: abc").contains("must be between 0 and 120");
        assertThatThrownBy(() -> SafeInput.readInt(new Scanner("x\n"), new PrintStream(new ByteArrayOutputStream()), "age: ", 0, 120)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void formatDependsOnTheLocale() {
        assertThat(String.format(Locale.US, "%,.2f", 1234567.891)).isEqualTo("1,234,567.89");
        assertThat(String.format(Locale.GERMANY, "%,.2f", 1234567.891)).isEqualTo("1.234.567,89");
        assertThat(String.format(Locale.ROOT, "%.2f", 0.5)).isEqualTo("0.50");
    }

    @Test
    void aWrongFormatSpecifierFailsAtRunTime() {
        assertThatThrownBy(() -> String.format("%d", 3.5)).hasMessage("d != java.lang.Double");
        assertThatThrownBy(() -> String.format("%s %s", "x")).hasMessage("Format specifier '%s'");
    }

    @Test
    void printingCharactersWithPlusAddsTheirCodes() {
        assertThat('a' + 'b').isEqualTo(195);
        assertThat("" + 'a' + 'b').isEqualTo("ab");
    }

    @Test
    void errorOutputIsAnotherStream() throws Exception {
        String[] captured = StandardStreams.run();
        assertThat(captured[0]).contains("result: 42").doesNotContain("warning");
        assertThat(captured[1]).contains("warning: something odd");
    }

    @Test
    void aBufferedReaderBeatsAScannerOnLargeInput() throws Exception {
        String text = ReadSpeed.numbers(300_000);
        assertThat(ReadSpeed.withScanner(text)).isEqualTo(ReadSpeed.withBufferedReader(text));
    }
}
