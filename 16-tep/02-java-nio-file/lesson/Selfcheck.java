import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Selfcheck {
    public static void main(String[] args) throws IOException {
        System.out.println(Path.of("a/b").resolve("../c").normalize());
        System.out.println(Path.of("a/b/../c").getNameCount());
        Path dir = Files.createTempDirectory("cafe");
        Path f = dir.resolve("q.txt");
        Files.writeString(f, "a");
        Files.writeString(f, "b");
        // APPEND nối vào, không thay thế
        Files.writeString(f, "c", StandardOpenOption.APPEND);
        System.out.println(Files.readString(f));
        Files.delete(f);
        Files.delete(dir);
    }
}
