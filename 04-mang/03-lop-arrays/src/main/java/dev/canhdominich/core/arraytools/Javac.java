package dev.canhdominich.core.arraytools;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

public final class Javac {

    private Javac() {
    }

    public static List<String> messages(String className, String source) {
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        JavaFileObject unit = new SimpleJavaFileObject(URI.create("string:///" + className + ".java"), JavaFileObject.Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                return source;
            }
        };
        ToolProvider.getSystemJavaCompiler().getTask(null, null, diagnostics, List.of("-proc:none", "-d", System.getProperty("java.io.tmpdir")), null, List.of(unit)).call();
        List<String> result = new ArrayList<>();
        for (Diagnostic<? extends JavaFileObject> d : diagnostics.getDiagnostics()) {
            if (d.getKind() == Diagnostic.Kind.ERROR) {
                result.add("line " + d.getLineNumber() + ": " + d.getMessage(Locale.ENGLISH).lines().findFirst().orElse(""));
            }
        }
        return result;
    }
}
