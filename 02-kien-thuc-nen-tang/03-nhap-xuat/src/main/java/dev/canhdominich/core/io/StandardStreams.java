package dev.canhdominich.core.io;

import java.io.IOException;
import java.nio.file.Path;

public class StandardStreams {

    public static class Child {
        public static void main(String[] args) {
            System.out.println("result: 42");
            System.err.println("warning: something odd");
            System.out.println("result: 43");
        }
    }

    public static String[] run() throws IOException, InterruptedException {
        Process process = new ProcessBuilder(Path.of(System.getProperty("java.home"), "bin", "java").toString(), "-cp", System.getProperty("java.class.path"), Child.class.getName()).start();
        String out = new String(process.getInputStream().readAllBytes());
        String err = new String(process.getErrorStream().readAllBytes());
        process.waitFor();
        return new String[] {out, err};
    }

    public static void main(String[] args) throws Exception {
        String[] captured = run();
        System.out.println("stdout captured: " + captured[0].replace("\n", "|"));
        System.out.println("stderr captured: " + captured[1].replace("\n", "|"));
    }
}
