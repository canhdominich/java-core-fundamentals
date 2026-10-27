import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Deeper {
    public static void main(String[] args) throws IOException {
        File dir = Files.createTempDirectory("cafe").toFile();
        File file = new File(dir, "b.bin");
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(200);
        }
        // Byte 200 được Java lưu là -56, nhưng read() trả về 200
        byte stored = Files.readAllBytes(file.toPath())[0];
        try (FileInputStream in = new FileInputStream(file)) {
            System.out.println("byte value " + stored + ", read() gives " + in.read() + ", at the end " + in.read());
        }
        File nested = new File(dir, "a/b");
        System.out.println("mkdir " + nested.mkdir() + ", mkdirs " + nested.mkdirs());
        System.out.println("delete non-empty " + dir.delete() + ", still there " + dir.exists());
        new File(dir, "a/b").delete();
        new File(dir, "a").delete();

        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            // Dù ngoại lệ bay ra, close của try vẫn flush nên tệp có chữ
            out.write("Latte");
            throw new IllegalStateException("boom");
        } catch (IllegalStateException e) {
            System.out.println("exception " + e.getMessage() + ", length after " + file.length());
        }

        for (int chars : new int[] {8191, 8192, 8193, 16384, 16385}) {
            try (BufferedWriter out = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
                out.write("x".repeat(chars));
                System.out.println(chars + " characters written, length before close " + file.length());
            }
        }
        file.delete();
        System.out.println("delete empty " + dir.delete());
    }
}
