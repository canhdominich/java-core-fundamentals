import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class CountLines {
    static int countLines(File file, String word) throws IOException {
        int count = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = in.readLine()) != null) {
                // Đếm dòng có chứa từ cần tìm
                if (line.contains(word)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        File dir = Files.createTempDirectory("cafe").toFile();
        File file = new File(dir, "sales.txt");
        try (FileWriter out = new FileWriter(file, StandardCharsets.UTF_8)) {
            out.write("Latte 2\nMocha 1\nLatte 3\nTea 1\n");
        }
        System.out.println("Latte " + countLines(file, "Latte"));
        System.out.println("Cocoa " + countLines(file, "Cocoa"));
        file.delete();
        dir.delete();
    }
}
