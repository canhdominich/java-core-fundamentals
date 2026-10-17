package dev.canhdominich.core.jvm;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JitWarmup {

    public record Result(String mode, List<Long> roundMillis) {
        public long total() {
            return roundMillis.stream().mapToLong(Long::longValue).sum();
        }
    }

    public static Result run(String mode, String flag, int rounds) throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add(Path.of(System.getProperty("java.home"), "bin", "java").toString());
        if (flag != null) {
            command.add(flag);
        }
        command.addAll(List.of("-cp", System.getProperty("java.class.path"), HotLoop.class.getName(), String.valueOf(rounds)));
        Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
        String output = new String(process.getInputStream().readAllBytes()).trim();
        process.waitFor();
        String[] parts = output.split("\\|")[0].trim().split(" ");
        List<Long> millis = new ArrayList<>();
        for (String part : parts) {
            millis.add(Long.parseLong(part));
        }
        return new Result(mode, millis);
    }

    public static void main(String[] args) throws Exception {
        int rounds = args.length > 0 && !args[0].equals("none") ? Integer.parseInt(args[0]) : 300_000_000;
        for (Result result : List.of(run("default (interpreter + JIT)", null, rounds), run("-Xint (interpreter only)", "-Xint", rounds))) {
            System.out.printf("%-30s rounds(ms)=%s total=%d ms%n", result.mode(), result.roundMillis(), result.total());
        }
    }
}
