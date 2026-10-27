import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class ArchiveReports {
    public static void main(String[] args) throws IOException {
        Path root = Files.createTempDirectory("cafe");
        Path month = root.resolve("reports").resolve("2026-10");

        // Tạo cả thư mục cha còn thiếu
        Files.createDirectories(month);
        System.out.println("step 1: " + Files.isDirectory(month));

        Path day1 = month.resolve("day1.txt");
        Path day2 = month.resolve("day2.txt");
        Files.writeString(day1, "Latte 12\nMocha 7\n");
        Files.writeString(day2, "Latte 5\nTea 9\n");
        System.out.println("step 2: " + Files.size(day1) + " " + Files.size(day2));

        System.out.println("step 3: " + Files.readAllLines(day1));

        // APPEND giữ hai dòng cũ
        Files.writeString(day1, "Tea 3\n", StandardOpenOption.APPEND);
        System.out.println("step 4: " + Files.readAllLines(day1).size());

        Path archive = root.resolve("archive");
        Files.createDirectories(archive);
        Files.copy(day1, archive.resolve("day1.txt"));
        // Lần hai phải có REPLACE_EXISTING
        Files.copy(day1, archive.resolve("day1.txt"), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("step 5: " + Files.readAllLines(archive.resolve("day1.txt")));

        Files.delete(archive.resolve("day1.txt"));
        Files.delete(archive);
        Files.delete(day1);
        Files.delete(day2);
        Files.delete(month);
        Files.delete(month.getParent());
        Files.delete(root);
    }
}
