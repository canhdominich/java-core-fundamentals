import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Breakages {
    public static void main(String[] args) throws IOException {
        Path dir = Files.createTempDirectory("cafe");
        Path file = dir.resolve("day1.txt");

        try {
            // Lỗi 1: tệp chưa từng được tạo
            Files.readString(dir.resolve("missing.txt"));
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage().replace(dir.toString(), "<dir>"));
        }

        Files.writeString(file, "first");
        // Lỗi 2: lần ghi thứ hai thay thế lặng lẽ nội dung cũ
        Files.writeString(file, "second");
        System.out.println("after two writes: " + Files.readString(file));

        Path copy = dir.resolve("copy.txt");
        Files.copy(file, copy);
        try {
            Files.copy(file, copy);
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage().replace(dir.toString(), "<dir>"));
        }

        try {
            // Lỗi 4: thư mục còn tệp bên trong
            Files.delete(dir);
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage().replace(dir.toString(), "<dir>"));
        }

        Files.delete(copy);
        Files.delete(file);
        Files.delete(dir);
    }
}
