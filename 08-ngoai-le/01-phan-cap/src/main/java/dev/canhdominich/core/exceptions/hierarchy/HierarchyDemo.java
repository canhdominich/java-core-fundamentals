package dev.canhdominich.core.exceptions.hierarchy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HierarchyDemo {

    public static List<String> chains() {
        List<String> out = new ArrayList<>();
        out.add(Hierarchy.chain(ArithmeticException.class));
        out.add(Hierarchy.chain(FileNotFoundException.class));
        out.add(Hierarchy.chain(OutOfMemoryError.class));
        out.add(Hierarchy.chain(InterruptedException.class));
        return out;
    }

    public static List<String> kinds() {
        List<String> out = new ArrayList<>();
        out.add("NullPointerException " + Hierarchy.kind(NullPointerException.class));
        out.add("IOException " + Hierarchy.kind(IOException.class));
        out.add("StackOverflowError " + Hierarchy.kind(StackOverflowError.class));
        out.add("Exception " + Hierarchy.kind(Exception.class));
        out.add("Throwable " + Hierarchy.kind(Throwable.class));
        return out;
    }

    public static String uncaught() {
        try {
            String java = System.getProperty("java.home") + "/bin/java";
            Process process = new ProcessBuilder(java, "-classpath", System.getProperty("java.class.path"),
                    "dev.canhdominich.core.exceptions.hierarchy.Boom").redirectErrorStream(false).start();
            String stdout = new String(process.getInputStream().readAllBytes()).trim();
            String stderr = new String(process.getErrorStream().readAllBytes()).trim();
            int exit = process.waitFor();
            String[] lines = stderr.split("\n");
            return "stdout [" + stdout + "], exit code " + exit + ", first stderr line [" + lines[0].trim() + "], then "
                    + (lines.length - 1) + " lines of frames, first frame [" + lines[1].trim().replaceAll("\\(.*\\)", "(...)") + "]";
        } catch (Exception e) {
            return "could not run: " + e;
        }
    }

    public static void main(String[] args) {
        for (String chain : chains()) {
            System.out.println("chain: " + chain);
        }
        for (String kind : kinds()) {
            System.out.println("kind:  " + kind);
        }
        for (String line : CommonExceptions.catalogue()) {
            System.out.println(line);
        }
        System.out.println("uncaught exception in a new process: " + uncaught());
        String unreported = "class A { void f() { throw new Exception(\"x\"); } }";
        String neverThrown = "import java.io.IOException; class A { void f() { try { int x = 1; } catch (IOException e) { } } }";
        String subclassAfterSuper = "import java.io.*; class A { void f() { try { new FileInputStream(\"x\"); } catch (IOException e) { } catch (FileNotFoundException e) { } } }";
        String uncheckedOk = "class A { void f() { throw new IllegalStateException(\"x\"); } }";
        String throwNonThrowable = "class A { void f() { throw new String(\"x\"); } }";
        System.out.println("throw a checked exception  = " + Javac.messages("A", unreported));
        System.out.println("catch one never thrown     = " + Javac.messages("A", neverThrown));
        System.out.println("subclass after superclass  = " + Javac.messages("A", subclassAfterSuper));
        System.out.println("throw an unchecked one     = " + Javac.messages("A", uncheckedOk));
        System.out.println("throw a String             = " + Javac.messages("A", throwNonThrowable));
    }
}
