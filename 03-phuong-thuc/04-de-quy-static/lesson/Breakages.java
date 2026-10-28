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

    static int broken(int floors) {
        return floors + broken(floors - 1);
    }

    static int cupsBad(int floors) {
        if (floors == 1) { // chỉ bắt đúng 1, nên số nhỏ hơn 1 trượt qua trường hợp cơ sở
            return 1;
        }
        return floors + cupsBad(floors - 1);
    }

    static String overflow(Runnable action) {
        try {
            action.run();
            return "no error";
        } catch (StackOverflowError e) {
            return "StackOverflowError";
        }
    }

    public static void main(String[] args) {
        System.out.println("1: " + overflow(() -> broken(4)));
        System.out.println("2: cupsBad(4) works, " + cupsBad(4) + ", but cupsBad(0) gives " + overflow(() -> cupsBad(0)));
        System.out.println("3: " + messages("class A { int value; static int read() { return value; } }"));
        System.out.println("4: " + messages("class A { void work() { } static void run() { work(); } }"));
        System.out.println("5: " + messages("class A { static A self() { return this; } }"));
        System.out.println("6: " + messages("class A { static int total = 3; int read() { return total; } }"));
        System.out.println("7: " + messages("class A { int height; static void reset() { height = 0; } }"));
    }
}
