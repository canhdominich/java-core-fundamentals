package dev.canhdominich.core.hello;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class LaunchModes {

    static long median(long[] values) {
        long[] copy = values.clone();
        Arrays.sort(copy);
        return copy[copy.length / 2];
    }

    public static void main(String[] args) throws Exception {
        String source = Files.readString(Path.of("src/main/java/dev/canhdominich/core/hello/Hello.java")).replace("package dev.canhdominich.core.hello;", "");
        Path dir = Files.createTempDirectory("modes");
        Files.writeString(dir.resolve("Hello.java"), source);
        Compile.Result compiled = Compile.source("Hello.java", source);
        long[] twoStep = new long[5];
        long[] direct = new long[5];
        Run.Outcome last = null;
        for (int i = 0; i < 5; i++) {
            long start = System.nanoTime();
            last = Run.java(compiled.outputDirectory(), "-cp", ".", "Hello", "Lan");
            twoStep[i] = (System.nanoTime() - start) / 1_000_000;
            start = System.nanoTime();
            Run.Outcome single = Run.java(dir, "Hello.java", "Lan");
            direct[i] = (System.nanoTime() - start) / 1_000_000;
            if (!single.firstLine().equals(last.firstLine())) {
                throw new IllegalStateException(single.firstLine());
            }
        }
        System.out.println("compiled once, java Hello Lan : " + last.firstLine() + "  median " + median(twoStep) + " ms");
        System.out.println("java Hello.java Lan (source)  : " + last.firstLine() + "  median " + median(direct) + " ms");
    }
}
