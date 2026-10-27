import java.nio.file.Files;
import java.nio.file.Path;

public class ToolBox {
    // Thử cả tên không đuôi (Linux, macOS) và tên có đuôi .exe (Windows)
    static boolean has(String home, String tool) {
        return Files.exists(Path.of(home, "bin", tool)) || Files.exists(Path.of(home, "bin", tool + ".exe"));
    }

    static void show(String home, String tool) {
        System.out.println(tool + " " + has(home, tool));
    }

    public static void main(String[] args) {
        String home = System.getProperty("java.home");
        show(home, "java");
        show(home, "keytool");
        show(home, "javac");
        show(home, "jar");
        show(home, "javap");
        // Có lệnh javac thì đây là JDK, không thì chỉ là runtime
        if (has(home, "javac")) {
            System.out.println("verdict: JDK");
        } else {
            System.out.println("verdict: runtime only, no javac command");
        }
    }
}
