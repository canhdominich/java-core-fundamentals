package dev.canhdominich.core.intro;

public class PlatformFacts {

    static String escape(String text) {
        return text.replace("\r", "\\r").replace("\n", "\\n");
    }

    public static void main(String[] args) {
        System.out.println("os.name        = " + System.getProperty("os.name"));
        System.out.println("os.arch        = " + System.getProperty("os.arch"));
        System.out.println("file.separator = " + System.getProperty("file.separator"));
        System.out.println("path.separator = " + System.getProperty("path.separator"));
        System.out.println("line.separator = " + escape(System.lineSeparator()));
        System.out.println("java.vm.name   = " + System.getProperty("java.vm.name"));
        System.out.println("java.version   = " + System.getProperty("java.version"));
    }
}
