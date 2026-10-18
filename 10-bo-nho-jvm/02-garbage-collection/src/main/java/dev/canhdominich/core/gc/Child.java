package dev.canhdominich.core.gc;

import java.util.ArrayList;
import java.util.List;

public final class Child {

    private Child() {
    }

    public static List<String> run(String mainClass, String... jvmFlags) {
        try {
            List<String> command = new ArrayList<>();
            command.add(System.getProperty("java.home") + "/bin/java");
            command.addAll(List.of(jvmFlags));
            command.add("-classpath");
            command.add(System.getProperty("java.class.path"));
            command.add(mainClass);
            Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
            String output = new String(process.getInputStream().readAllBytes());
            process.waitFor();
            return List.of(output.split("\n"));
        } catch (Exception e) {
            return List.of("could not run: " + e);
        }
    }
}
