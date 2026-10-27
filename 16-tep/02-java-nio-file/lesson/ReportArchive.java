import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReportArchive {
    private final Path reports;
    private final Path backups;

    public ReportArchive(Path root) throws IOException {
        this.reports = root.resolve("reports");
        this.backups = root.resolve("backups");
        Files.createDirectories(reports);
        Files.createDirectories(backups);
    }

    public Path save(String day, String text) throws IOException {
        return Files.writeString(reports.resolve(day + ".txt"), text);
    }

    public List<String> read(String day) throws IOException {
        Path file = reports.resolve(day + ".txt");
        // Tệp chưa có thì trả về danh sách rỗng
        if (!Files.exists(file)) {
            return new ArrayList<>();
        }
        return Files.readAllLines(file);
    }

    public void backup(String day) throws IOException {
        Files.copy(reports.resolve(day + ".txt"), backups.resolve(day + ".txt"), StandardCopyOption.REPLACE_EXISTING);
    }

    public long count() throws IOException {
        // Files.list trả về stream nên phải đóng
        try (Stream<Path> files = Files.list(reports)) {
            return files.filter(p -> p.toString().endsWith(".txt")).count();
        }
    }

    public static void main(String[] args) throws IOException {
        Path root = Files.createTempDirectory("cafe");
        ReportArchive archive = new ReportArchive(root);
        System.out.println("missing: " + archive.read("day1") + " count " + archive.count());
        archive.save("day1", "Latte 12\n");
        archive.save("day2", "Tea 9\n");
        archive.save("day1", "Latte 12\nMocha 7\n");
        System.out.println("day1: " + archive.read("day1") + " count " + archive.count());
        archive.backup("day1");
        archive.backup("day1");
        System.out.println("backup: " + Files.readAllLines(root.resolve("backups").resolve("day1.txt")));
        try (Stream<Path> all = Files.walk(root)) {
            all.sorted(java.util.Comparator.reverseOrder()).forEach(p -> p.toFile().delete());
        }
    }
}
