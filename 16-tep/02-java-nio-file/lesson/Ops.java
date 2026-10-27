import java.io.IOException;
import java.nio.file.*;

public class Ops {
    public static void main(String[] args) throws IOException {
        Path dir = Files.createTempDirectory("cafe");
        Path a = dir.resolve("a.txt");
        Path b = dir.resolve("b.txt");
        Files.writeString(a, "payload");
        Files.copy(a, b);
        try {
            Files.copy(a, b);
        } catch (FileAlreadyExistsException e) {
            System.out.println("second copy: " + e.getClass().getSimpleName());
        }
        // REPLACE_EXISTING cho phép ghi đè tệp đích
        Files.copy(a, b, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("with REPLACE_EXISTING: copied");
        // move đổi tên, tệp cũ biến mất
        Files.move(b, dir.resolve("c.txt"));
        System.out.println("after move: old exists " + Files.exists(b) + ", new exists " + Files.exists(dir.resolve("c.txt")));
        Files.delete(a);
        try {
            Files.delete(a);
        } catch (NoSuchFileException e) {
            System.out.println("delete twice: " + e.getClass().getSimpleName());
        }
        // Tệp không còn thì trả về false, không ném lỗi
        System.out.println("deleteIfExists on a missing file: " + Files.deleteIfExists(a));
        try {
            Files.delete(dir);
        } catch (DirectoryNotEmptyException e) {
            System.out.println("delete folder with a file: " + e.getClass().getSimpleName());
        }
        Files.delete(dir.resolve("c.txt"));
        Files.delete(dir);
    }
}
