package dev.canhdominich.core.exceptions.hierarchy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HierarchyTest {

    @Test
    void everyExceptionDescendsFromThrowable() {
        assertThat(HierarchyDemo.chains()).containsExactly(
                "ArithmeticException extends RuntimeException extends Exception extends Throwable extends Object",
                "FileNotFoundException extends IOException extends Exception extends Throwable extends Object",
                "OutOfMemoryError extends VirtualMachineError extends Error extends Throwable extends Object",
                "InterruptedException extends Exception extends Throwable extends Object");
    }

    @Test
    void onlyRuntimeExceptionsAndErrorsAreUnchecked() {
        assertThat(HierarchyDemo.kinds()).containsExactly(
                "NullPointerException unchecked (RuntimeException)", "IOException checked", "StackOverflowError unchecked (Error)",
                "Exception checked", "Throwable checked");
    }

    @Test
    void commonExceptionsCarryUsefulMessages() {
        assertThat(CommonExceptions.describe(() -> System.out.print(10 / (args().length)))).isEqualTo("ArithmeticException: / by zero");
        assertThat(CommonExceptions.describe(() -> Integer.parseInt("12x"))).isEqualTo("NumberFormatException: For input string: \"12x\"");
        assertThat(CommonExceptions.describe(() -> Integer.parseInt(null))).isEqualTo("NumberFormatException: Cannot parse null string");
        assertThat(CommonExceptions.describe(() -> new java.util.ArrayList<String>(-1))).isEqualTo("IllegalArgumentException: Illegal Capacity: -1");
        assertThat(CommonExceptions.describe(() -> {
        })).isEqualTo("no exception");
    }

    private static String[] args() {
        return new String[0];
    }

    @Test
    void theCatalogueCoversTheThirteenExceptions() {
        assertThat(CommonExceptions.catalogue()).hasSize(13);
        assertThat(CommonExceptions.catalogue().get(1)).isEqualTo(
                "null reference           : NullPointerException: Cannot invoke \"String.length()\" because \"nothing\" is null");
    }

    @Test
    void anUncaughtExceptionPrintsAStackTraceStopsTheProgramAndExitsWithOne() {
        assertThat(HierarchyDemo.uncaught()).isEqualTo(
                "stdout [before], exit code 1, first stderr line [Exception in thread \"main\" java.lang.IllegalStateException: boom], then 4 lines of frames, first frame [at dev.canhdominich.core.exceptions.hierarchy.Boom.level3(...)]");
    }

    @Test
    void theCompilerChecksCheckedExceptionsOnly() {
        assertThat(Javac.messages("A", "class A { void f() { throw new Exception(\"x\"); } }"))
                .containsExactly("line 1: unreported exception java.lang.Exception; must be caught or declared to be thrown");
        assertThat(Javac.messages("A", "class A { void f() { throw new IllegalStateException(\"x\"); } }")).isEmpty();
        assertThat(Javac.messages("A", "import java.io.IOException; class A { void f() { try { int x = 1; } catch (IOException e) { } } }"))
                .containsExactly("line 1: exception java.io.IOException is never thrown in body of corresponding try statement");
        assertThat(Javac.messages("A", "import java.io.*; class A { void f() { try { new FileInputStream(\"x\"); } catch (IOException e) { } catch (FileNotFoundException e) { } } }"))
                .containsExactly("line 1: exception java.io.FileNotFoundException has already been caught");
    }
}
