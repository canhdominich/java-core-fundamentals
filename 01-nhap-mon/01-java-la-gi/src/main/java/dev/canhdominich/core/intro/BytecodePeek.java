package dev.canhdominich.core.intro;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.spi.ToolProvider;

public class BytecodePeek {

    public static List<String> disassemble(int release) throws IOException {
        Path dir = Files.createTempDirectory("peek");
        Path file = dir.resolve("Greeter.class");
        Files.write(file, InMemoryCompiler.compile("Greeter", Sources.GREETER, release));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintWriter out = new PrintWriter(buffer, true);
        ToolProvider javap = ToolProvider.findFirst("javap").orElseThrow();
        javap.run(out, out, "-c", file.toString());
        out.flush();
        return buffer.toString().lines().filter(line -> line.contains("invoke") || line.contains("new ")).map(String::strip).toList();
    }

    public static void main(String[] args) throws IOException {
        for (int release : new int[] {8, 17}) {
            System.out.println("--release " + release);
            disassemble(release).forEach(line -> System.out.println("  " + line));
        }
    }
}
