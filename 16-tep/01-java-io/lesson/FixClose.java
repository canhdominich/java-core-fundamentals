import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FixClose {
    public static void main(String[] args) throws IOException {
        File dir = Files.createTempDirectory("cafe").toFile();
        File file = new File(dir, "orders.txt");
        // close của try chạy trước println nên tệp đã đủ chữ
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            out.write("Latte\n");
            out.write("Mocha\n");
        }
        System.out.println("length " + file.length());
        file.delete();
        dir.delete();
    }
}
