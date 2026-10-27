import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class LazyLines {
    public static void main(String[] args) throws IOException {
        Path file = Files.createTempFile("cafe", ".log");
        for (int total : new int[] {8, 100000}) {
            int errorAt = total * 3 / 8;
            List<String> lines = new ArrayList<>();
            for (int i = 1; i <= total; i++) {
                lines.add(i == errorAt ? "ERROR line " + i : "INFO line " + i);
            }
            Files.write(file, lines);
            AtomicInteger seen = new AtomicInteger();
            // Stream giữ tệp mở nên phải đóng bằng try
            try (Stream<String> stream = Files.lines(file)) {
                // peek đếm số dòng stream thật sự đã đọc
                String found = stream.peek(line -> seen.incrementAndGet())
                        // findFirst phía sau dừng ngay khi có kết quả, các dòng sau không bị đọc
                        .filter(line -> line.startsWith("ERROR")).findFirst().orElse("none");
                System.out.println(total + " lines: readAllLines loads " + Files.readAllLines(file).size()
                        + ", lazy stream read " + seen.get() + " and found '" + found + "'");
            }
        }
        Files.delete(file);
    }
}
