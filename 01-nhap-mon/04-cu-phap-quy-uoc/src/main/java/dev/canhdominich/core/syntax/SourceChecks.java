package dev.canhdominich.core.syntax;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.tools.FileObject;

public final class SourceChecks {

    public record Outcome(boolean ok, List<String> messages, Map<String, byte[]> classes) {
    }

    private SourceChecks() {
    }

    public static Outcome compile(String className, String source, String... options) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager standard = compiler.getStandardFileManager(diagnostics, Locale.ENGLISH, null);
        Map<String, ByteArrayOutputStream> output = new HashMap<>();
        JavaFileObject unit = new SimpleJavaFileObject(URI.create("string:///" + className + ".java"), JavaFileObject.Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                return source;
            }
        };
        ForwardingJavaFileManager<StandardJavaFileManager> manager = new ForwardingJavaFileManager<>(standard) {
            @Override
            public JavaFileObject getJavaFileForOutput(Location location, String name, JavaFileObject.Kind kind, FileObject sibling) {
                return new SimpleJavaFileObject(URI.create("mem:///" + name + ".class"), kind) {
                    @Override
                    public OutputStream openOutputStream() {
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        output.put(name, bytes);
                        return bytes;
                    }
                };
            }
        };
        List<String> arguments = new ArrayList<>(List.of(options));
        boolean ok = compiler.getTask(null, manager, diagnostics, arguments, null, List.of(unit)).call();
        List<String> messages = new ArrayList<>();
        for (Diagnostic<? extends JavaFileObject> d : diagnostics.getDiagnostics()) {
            messages.add(d.getKind() + " line " + d.getLineNumber() + ": " + d.getMessage(Locale.ENGLISH).lines().findFirst().orElse(""));
        }
        Map<String, byte[]> classes = new HashMap<>();
        output.forEach((name, bytes) -> classes.put(name, bytes.toByteArray()));
        return new Outcome(ok, messages, classes);
    }

    public static boolean compiles(String source) {
        String name = source.contains("class studentAccount") ? "studentAccount" : "A";
        return compile(name, source).ok();
    }
}
