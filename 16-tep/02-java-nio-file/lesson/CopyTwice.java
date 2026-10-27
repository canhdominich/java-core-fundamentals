import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CopyTwice {
    public static void main(String[] args) throws IOException {
        Path dir = Files.createTempDirectory("cafe");
        Path source = dir.resolve("day1.txt");
        Path backup = dir.resolve("day1.bak");
        Files.writeString(source, "Latte 12\n");
        // Có REPLACE_EXISTING thì chạy lần nào cũng đúng
        Files.copy(source, backup, StandardCopyOption.REPLACE_EXISTING);
        Files.writeString(source, "Latte 12\nMocha 7\n");
        Files.copy(source, backup, StandardCopyOption.REPLACE_EXISTING);
        System.out.println(Files.readAllLines(backup));
        Files.delete(backup);
        Files.delete(source);
        Files.delete(dir);
    }
}
