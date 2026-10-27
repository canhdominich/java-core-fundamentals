import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class TotalCups {
    static int totalCups(Path file) throws IOException {
        // Đóng stream bằng try dù dòng return nằm bên trong
        try (Stream<String> lines = Files.lines(file)) {
            return lines.mapToInt(line -> Integer.parseInt(line.split(" ")[1])).sum();
        }
    }

    public static void main(String[] args) throws IOException {
        Path file = Files.createTempFile("cafe", ".txt");
        Files.writeString(file, "Latte 2\nMocha 1\nLatte 3\nTea 1\n");
        System.out.println("total " + totalCups(file));
        Files.delete(file);
    }
}
