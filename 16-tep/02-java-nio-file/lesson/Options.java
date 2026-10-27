import java.io.IOException;
import java.nio.file.*;

public class Options {
    public static void main(String[] args) throws IOException {
        Path file = Files.createTempFile("cafe", ".txt");
        Files.writeString(file, "first");
        Files.writeString(file, "second");  // Mặc định là thay thế nội dung cũ
        System.out.println("after two writes: " + Files.readString(file));
        Files.writeString(file, "!", StandardOpenOption.APPEND);  // APPEND nối vào cuối, giữ nội dung cũ
        System.out.println("after append: " + Files.readString(file));
        try {
            Files.writeString(file, "again", StandardOpenOption.CREATE_NEW);  // CREATE_NEW chỉ chạy khi tệp chưa có
        } catch (FileAlreadyExistsException e) {
            System.out.println("CREATE_NEW on an existing file: " + e.getClass().getSimpleName());
        }
        System.out.println("content kept: " + Files.readString(file));
        Files.delete(file);
    }
}
