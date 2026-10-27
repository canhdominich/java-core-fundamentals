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
    static long withVat(long net) {
        return Math.round(net * 1.1);
    }

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
        long net = 5;
        withVat(net); // bỏ kết quả nên net không đổi
        System.out.println("1: ignored return value, net = " + net);
        System.out.println("2: " + messages("class A { int sign(int x) { if (x > 0) { return 1; } } }"));
        System.out.println("3: " + messages("class A { void log(String s) { } void g() { int n = log(\"x\"); } }"));
        System.out.println("4: " + messages("class A { int add(int a, int b) { return a + b; } int g() { return add(1); } }"));
        System.out.println("5: " + messages("class A { int f() { return 1; System.out.println(2); } }"));
    }
}
