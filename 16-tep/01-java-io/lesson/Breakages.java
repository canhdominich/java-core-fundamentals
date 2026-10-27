import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Breakages {
    public static void main(String[] args) throws IOException {
        File dir = Files.createTempDirectory("cafe").toFile();
        File file = new File(dir, "orders.txt");

        // Lỗi 1: quên close nên chữ còn nằm trong bộ đệm
        BufferedWriter forgot = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
        forgot.write("hello");
        System.out.println("forgot to close: length " + file.length());
        forgot.close();

        try {
            // Lỗi 2: mở tệp không tồn tại
            new FileReader(new File(dir, "missing.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage().replace(dir.getPath(), "<dir>"));
        }

        // Lỗi 3: không có tham số true nên lần mở thứ hai xoá nội dung cũ
        try (FileWriter first = new FileWriter(file, StandardCharsets.UTF_8)) {
            first.write("one\ntwo\n");
        }
        try (FileWriter second = new FileWriter(file, StandardCharsets.UTF_8)) {
            second.write("three\n");
        }
        System.out.println("after second writer: " + Files.readAllLines(file.toPath()));

        Files.write(file.toPath(), "Latte\nMocha\nTea\n".getBytes(StandardCharsets.UTF_8));
        try (BufferedReader in = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            // Lỗi 4: mỗi vòng gọi readLine hai lần nên nuốt mất dòng
            while (in.readLine() != null) {
                System.out.println("printed: " + in.readLine());
            }
        }

        file.delete();
        dir.delete();
    }
}
