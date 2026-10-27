import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Deeper {
    public static void main(String[] args) throws IOException {
        Path root = Files.createTempDirectory("cafe");
        Path inside = root.resolve("src/util");
        Files.createDirectories(inside);
        Files.writeString(root.resolve("src/App.java"), "class App {}\n");
        Files.writeString(inside.resolve("Helper.java"), "class Helper {}\n");
        Files.writeString(root.resolve("src/notes.txt"), "notes\n");

        List<String> found = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(root)) {
            walk.filter(p -> p.toString().endsWith(".java")).forEach(p -> found.add(root.relativize(p).toString()));
        }
        found.sort(null);
        System.out.println("walk finds " + found);
        List<String> top = new ArrayList<>();
        try (Stream<Path> list = Files.list(root.resolve("src"))) {
            list.forEach(p -> top.add(p.getFileName().toString()));
        }
        top.sort(null);
        System.out.println("list shows only one level " + top);

        String userInput = "../../etc/passwd";
        // Đầu vào độc hại có đoạn ..
        Path target = root.resolve(userInput);
        System.out.println("naive text check " + target.toString().startsWith(root.toString())
                + ", after normalize " + target.normalize().startsWith(root));

        Path sibling = Path.of("/srv/cafe2/report.txt");
        System.out.println("string startsWith " + sibling.toString().startsWith("/srv/cafe")
                + ", Path startsWith " + sibling.startsWith(Path.of("/srv/cafe")));

        Path bad = root.resolve("bad.txt");
        // Byte E9 là chữ é của ISO-8859-1, không hợp lệ trong UTF-8
        Files.write(bad, new byte[] {'C', 'a', 'f', (byte) 0xE9});
        try {
            Files.readString(bad);
        } catch (MalformedInputException e) {
            System.out.println("readString threw " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        try (Stream<Path> all = Files.walk(root)) {
            all.sorted(Comparator.reverseOrder()).forEach(p -> p.toFile().delete());
        }
    }
}
