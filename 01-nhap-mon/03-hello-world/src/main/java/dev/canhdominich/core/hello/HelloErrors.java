package dev.canhdominich.core.hello;

import java.io.IOException;
import java.nio.file.Path;

public class HelloErrors {

    static void compileCase(String title, String fileName, String source) throws IOException {
        Compile.Result result = Compile.source(fileName, source);
        System.out.println("[compile] " + title + " -> " + (result.ok() ? "compiled" : result.messages().get(0)));
    }

    static void runCase(String title, String source, String... arguments) throws Exception {
        Compile.Result result = Compile.source("Hello.java", source);
        Run.Outcome outcome = Run.java(result.outputDirectory(), arguments);
        System.out.println("[run]     " + title + " -> exit " + outcome.exitCode() + ", " + outcome.firstLine());
    }

    public static void main(String[] args) throws Exception {
        compileCase("missing semicolon", "Hello.java", BrokenPrograms.MISSING_SEMICOLON);
        compileCase("unknown symbol", "Hello.java", BrokenPrograms.UNKNOWN_SYMBOL);
        compileCase("public class in the wrong file", "Hello.java", BrokenPrograms.WRONG_FILE_NAME);
        runCase("no main method", BrokenPrograms.NO_MAIN, "-cp", ".", "Hello");
        runCase("wrong class name", BrokenPrograms.EXIT_THREE, "-cp", ".", "Helo");
        runCase("class name with .class", BrokenPrograms.EXIT_THREE, "-cp", ".", "Hello.class");
        runCase("exception in main", BrokenPrograms.THROWS_AT_RUNTIME, "-cp", ".", "Hello");
        runCase("System.exit(3)", BrokenPrograms.EXIT_THREE, "-cp", ".", "Hello");
        Path none = Path.of(System.getProperty("java.io.tmpdir"));
        Run.Outcome noClasspath = Run.java(none, "Hello");
        System.out.println("[run]     no classpath -> exit " + noClasspath.exitCode() + ", " + noClasspath.firstLine());
    }
}
