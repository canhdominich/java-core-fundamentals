package dev.canhdominich.core.jvm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class JdkLayout {

    public static List<String> tools(Path javaHome) throws IOException {
        try (Stream<Path> bin = Files.list(javaHome.resolve("bin"))) {
            return bin.map(path -> path.getFileName().toString()).sorted().toList();
        }
    }

    public static boolean hasCompiler() {
        return javax.tools.ToolProvider.getSystemJavaCompiler() != null;
    }

    public static void main(String[] args) throws IOException {
        Path home = Path.of(System.getProperty("java.home"));
        List<String> tools = tools(home);
        System.out.println("java.home      = " + home);
        System.out.println("tools in bin   = " + tools.size());
        for (String name : new String[] {"java", "javac", "jar", "javadoc", "javap", "jlink", "jshell"}) {
            System.out.printf("  %-8s %s%n", name, tools.contains(name) ? "present" : "missing");
        }
        System.out.println("modules in boot layer = " + ModuleLayer.boot().modules().size());
        System.out.println("module jdk.compiler   = " + ModuleLayer.boot().findModule("jdk.compiler").isPresent());
        System.out.println("module java.base      = " + ModuleLayer.boot().findModule("java.base").isPresent());
        System.out.println("javac available to the program = " + hasCompiler());
    }
}
