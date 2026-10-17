package dev.canhdominich.core.hello;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public final class Compile {

    public record Result(boolean ok, List<String> messages, Path outputDirectory) {
    }

    private Compile() {
    }

    public static Result source(String fileName, String source) throws IOException {
        Path dir = Files.createTempDirectory("hello");
        Path file = Files.writeString(dir.resolve(fileName), source);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        try (StandardJavaFileManager manager = compiler.getStandardFileManager(diagnostics, Locale.ENGLISH, null)) {
            boolean ok = compiler.getTask(null, manager, diagnostics, List.of("-d", dir.toString()), null, manager.getJavaFileObjects(file)).call();
            List<String> messages = new ArrayList<>();
            for (Diagnostic<? extends JavaFileObject> d : diagnostics.getDiagnostics()) {
                if (d.getKind() == Diagnostic.Kind.ERROR) {
                    messages.add("line " + d.getLineNumber() + ": " + d.getMessage(Locale.ENGLISH).lines().findFirst().orElse(""));
                }
            }
            return new Result(ok, messages, dir);
        }
    }
}
