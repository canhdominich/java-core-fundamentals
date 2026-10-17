package dev.canhdominich.core.hello;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class Run {

    public record Outcome(int exitCode, List<String> lines) {
        public String firstLine() {
            return lines.isEmpty() ? "" : lines.get(0);
        }
    }

    private Run() {
    }

    public static Outcome java(Path workingDirectory, String... arguments) throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add(Path.of(System.getProperty("java.home"), "bin", "java").toString());
        command.addAll(List.of(arguments));
        Process process = new ProcessBuilder(command).directory(workingDirectory.toFile()).redirectErrorStream(true).start();
        String text = new String(process.getInputStream().readAllBytes()).trim();
        int exit = process.waitFor();
        return new Outcome(exit, text.lines().map(String::strip).toList());
    }
}
