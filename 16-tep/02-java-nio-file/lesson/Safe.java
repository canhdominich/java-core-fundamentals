import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Safe {
    static boolean isInside(Path root, String userInput) {
        Path base = root.toAbsolutePath().normalize();
        // Ghép rồi gọt .. để lộ vị trí thật
        Path target = base.resolve(userInput).normalize();
        // startsWith của Path so từng đoạn tên, không so từng ký tự
        return target.startsWith(base);
    }

    public static void main(String[] args) throws IOException {
        Path root = Files.createTempDirectory("cafe");
        for (String input : new String[] {"report.pdf", "sub/../ok.txt", "../../etc/passwd", "/etc/passwd"}) {
            System.out.println(input + " inside " + isInside(root, input));
        }
        Files.delete(root);
    }
}
