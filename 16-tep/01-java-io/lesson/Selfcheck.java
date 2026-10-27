import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Selfcheck {
    public static void main(String[] args) throws IOException {
        File dir = Files.createTempDirectory("cafe").toFile();
        File f = new File(dir, "q.txt");
        try (FileWriter w = new FileWriter(f, StandardCharsets.UTF_8)) {
            w.write("A\nB\n");
        }
        try (BufferedReader in = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8))) {
            System.out.println(in.readLine());
            System.out.println(in.readLine());
            System.out.println(in.readLine());
        }
        // Mở không có true rồi đóng ngay: tệp đã bị xoá sạch
        new FileWriter(f, StandardCharsets.UTF_8).close();
        System.out.println("after open and close: " + f.length());
        try (FileWriter w = new FileWriter(f, StandardCharsets.UTF_8, true)) {
            w.write("x\n");
        }
        System.out.println(Files.readAllLines(f.toPath()));
        f.delete();
        dir.delete();
    }
}
