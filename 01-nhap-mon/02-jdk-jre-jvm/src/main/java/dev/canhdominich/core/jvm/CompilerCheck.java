package dev.canhdominich.core.jvm;

import java.io.IOException;

public class CompilerCheck {

    public static String commandResult() {
        try {
            Process process = new ProcessBuilder("javac", "-version").redirectErrorStream(true).start();
            String text = new String(process.getInputStream().readAllBytes()).trim();
            return process.waitFor() == 0 ? "found, " + text : "failed, " + text;
        } catch (IOException e) {
            return "not found on PATH";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "interrupted";
        }
    }

    public static boolean apiAvailable() {
        return javax.tools.ToolProvider.getSystemJavaCompiler() != null;
    }

    public static void main(String[] args) {
        System.out.println("javac command : " + commandResult());
        System.out.println("compiler API  : " + (apiAvailable() ? "available (module jdk.compiler is in this runtime)" : "missing"));
    }
}
