import java.nio.file.Files;
import java.nio.file.Path;
import javax.tools.ToolProvider;

// Phiếu khám máy: hỏi hai câu độc lập về trình biên dịch
public class MachineCheck {
    public static void main(String[] args) {
        // Thư mục cài Java đang chạy chương trình này
        String home = System.getProperty("java.home");
        // Câu hỏi một: có file lệnh javac trong thư mục bin không (Windows có đuôi .exe)
        boolean hasCommand = Files.exists(Path.of(home, "bin", "javac"))
                || Files.exists(Path.of(home, "bin", "javac.exe"));
        // Câu hỏi hai: JVM có sẵn trình biên dịch trong bộ nhớ không, null nghĩa là không
        boolean hasApi = ToolProvider.getSystemJavaCompiler() != null;
        System.out.println("java.version  = " + System.getProperty("java.version"));
        System.out.println("java.home     = " + home);
        System.out.println("javac command = " + hasCommand);
        System.out.println("compiler API  = " + hasApi);
    }
}
