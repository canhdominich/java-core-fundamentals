import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Trace {
    public static void main(String[] args) throws IOException {
        File dir = Files.createTempDirectory("cafe").toFile();
        File f = new File(dir, "t.txt");
        // Khối 1: mở ghi đè, tệp bắt đầu từ rỗng
        try (FileWriter w = new FileWriter(f, StandardCharsets.UTF_8)) {
            w.write("A\n");
            w.write("B\n");
        }
        System.out.println("block 1: " + Files.readAllLines(f.toPath()) + " " + f.length());
        // Khối 2: tham số true giữ nội dung cũ
        try (FileWriter w = new FileWriter(f, StandardCharsets.UTF_8, true)) {
            w.write("C\n");
        }
        System.out.println("block 2: " + Files.readAllLines(f.toPath()) + " " + f.length());
        try (FileWriter w = new FileWriter(f, StandardCharsets.UTF_8)) {
            w.write("D\n");
        }
        System.out.println("block 3: " + Files.readAllLines(f.toPath()) + " " + f.length());
        f.delete();
        dir.delete();
    }
}
