import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class CopyCount {
    static int copyByByte(Path from, Path to) throws IOException {
        int calls = 0;
        try (InputStream in = new FileInputStream(from.toFile());
                OutputStream out = new FileOutputStream(to.toFile())) {
            int b;
            do {
                // Mỗi lần read lấy một byte, nên tốn nhiều lần gọi
                b = in.read();
                calls++;
                if (b != -1) {
                    out.write(b);
                }
            } while (b != -1);
        }
        return calls;
    }

    static int copyByBlock(Path from, Path to) throws IOException {
        int calls = 0;
        byte[] buffer = new byte[4096];
        try (InputStream in = new FileInputStream(from.toFile());
                OutputStream out = new FileOutputStream(to.toFile())) {
            int n;
            do {
                // Mỗi lần read lấy tới 4096 byte, trả về -1 khi hết
                n = in.read(buffer);
                calls++;
                if (n != -1) {
                    out.write(buffer, 0, n);
                }
            } while (n != -1);
        }
        return calls;
    }

    public static void main(String[] args) throws IOException {
        Path dir = Files.createTempDirectory("cafe");
        Path source = dir.resolve("data.bin");
        byte[] data = new byte[10000];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        Files.write(source, data);
        Path copy1 = dir.resolve("copy1.bin");
        Path copy2 = dir.resolve("copy2.bin");
        System.out.println("byte by byte: " + copyByByte(source, copy1) + " read calls");
        System.out.println("4096 block: " + copyByBlock(source, copy2) + " read calls");
        System.out.println("same content: " + (Arrays.equals(data, Files.readAllBytes(copy1)) && Arrays.equals(data, Files.readAllBytes(copy2))));
        for (Path p : new Path[] {source, copy1, copy2, dir}) {
            Files.delete(p);
        }
    }
}
