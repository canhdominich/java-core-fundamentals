package dev.canhdominich.core.jvm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class VersionMatrix {

    static final String HELLO = """
            public class Hello {
                public static void main(String[] args) {
                    System.out.println("hello from Java " + System.getProperty("java.specification.version"));
                }
            }
            """;

    private static final Pattern VERSIONS = Pattern.compile("class file version (\\d+\\.\\d+).*up to (\\d+\\.\\d+)");

    public static Path compile(int release, Path root) throws IOException {
        Path dir = Files.createDirectories(root.resolve("release-" + release));
        Path source = Files.writeString(dir.resolve("Hello.java"), HELLO);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int status = compiler.run(null, null, null, "--release", String.valueOf(release), "-d", dir.toString(), source.toString());
        if (status != 0) {
            throw new IllegalStateException("cannot compile for release " + release);
        }
        return dir;
    }

    public static String run(String javaExecutable, Path classes) throws IOException, InterruptedException {
        Process process = new ProcessBuilder(javaExecutable, "-cp", classes.toString(), "Hello").redirectErrorStream(true).start();
        String text = new String(process.getInputStream().readAllBytes()).trim();
        process.waitFor();
        String line = text.lines().filter(l -> l.startsWith("hello") || l.contains("UnsupportedClassVersionError")).findFirst().map(String::strip).orElse(text);
        Matcher matcher = VERSIONS.matcher(line);
        if (line.contains("UnsupportedClassVersionError") && matcher.find()) {
            return "UnsupportedClassVersionError: file is " + matcher.group(1) + ", this JVM supports up to " + matcher.group(2);
        }
        return line;
    }

    public static String label(String javaExecutable) throws IOException, InterruptedException {
        Process process = new ProcessBuilder(javaExecutable, "-version").redirectErrorStream(true).start();
        String text = new String(process.getInputStream().readAllBytes()).trim();
        process.waitFor();
        return text.lines().findFirst().orElse("?");
    }

    public static void main(String[] args) throws Exception {
        List<String> executables = new ArrayList<>();
        if (args.length > 0 && !args[0].equals("none")) {
            executables.addAll(List.of(args[0].split(",")));
        } else {
            executables.add(Path.of(System.getProperty("java.home"), "bin", "java").toString());
        }
        Path root = Files.createTempDirectory("matrix");
        List<Path> classes = new ArrayList<>();
        int[] releases = {8, 11, 17};
        for (int release : releases) {
            classes.add(compile(release, root));
        }
        for (String executable : executables) {
            System.out.println(label(executable));
            for (int i = 0; i < releases.length; i++) {
                System.out.printf("  class from --release %-2d : %s%n", releases[i], run(executable, classes.get(i)));
            }
        }
    }
}
