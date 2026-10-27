import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

public class Breakages {
    // biên dịch đoạn mã trong bộ nhớ và trả về dòng đầu của mỗi thông báo lỗi
    static List<String> messages(String source) {
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        JavaFileObject unit = new SimpleJavaFileObject(URI.create("string:///A.java"), JavaFileObject.Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                return source;
            }
        };
        ToolProvider.getSystemJavaCompiler().getTask(null, null, diagnostics, List.of("-proc:none", "-d", System.getProperty("java.io.tmpdir")), null, List.of(unit)).call();
        List<String> result = new ArrayList<>();
        for (Diagnostic<? extends JavaFileObject> d : diagnostics.getDiagnostics()) {
            if (d.getKind() == Diagnostic.Kind.ERROR) {
                result.add(d.getMessage(Locale.ENGLISH).lines().findFirst().orElse(""));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("1: " + messages("class A { int f(int x) { return x; } int f(int y) { return y; } }"));
        System.out.println("2: " + messages("class A { int f(int x) { return x; } long f(int x) { return x; } }"));
        System.out.println("3: " + messages("class A { String pick(String s) { return \"s\"; } String pick(StringBuilder b) { return \"b\"; } String g() { return pick(null); } }"));
        System.out.println("4: " + messages("class A { int f(int x) { return x; } int f(double x) { return 0; } int g() { return f(\"a\"); } }"));
        System.out.println("5: " + messages("class A { int f(int x) { return x; } int g() { return f(5L); } }"));
    }
}
