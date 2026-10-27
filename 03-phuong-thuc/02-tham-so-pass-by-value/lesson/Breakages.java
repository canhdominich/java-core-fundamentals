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

    static void swap(Order a, Order b) {
        Order temp = a;
        a = b; // chỉ đổi chỗ hai bản sao địa chỉ nằm trong tham số
        b = temp;
    }

    static String appendBang(String text) {
        text += "!"; // tạo chuỗi mới cho bản sao, chuỗi cũ không bị sửa
        return text;
    }

    public static void main(String[] args) {
        Order x = new Order(1);
        Order y = new Order(2);
        swap(x, y);
        System.out.println("1: after swap, x = " + x.qty + ", y = " + y.qty);
        String text = "hi";
        appendBang(text);
        System.out.println("2: after appendBang, text = " + text);
        System.out.println("3: " + messages("class A { void f(int... a, String b) { } }"));
        System.out.println("4: " + messages("class A { void f(final int x) { x = 5; } }"));
        System.out.println("5: " + messages("class A { int g() { return f(1, \"two\"); } int f(int... values) { return values.length; } }"));
    }

    static class Order {
        int qty;

        Order(int qty) {
            this.qty = qty;
        }
    }
}
