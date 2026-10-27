import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ShiftLog {
    // Đọc cả tệp ra danh sách, dùng lại ở nhiều bước
    static List<String> read(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            // Dừng khi readLine trả về null
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static void main(String[] args) throws IOException {
        File dir = Files.createTempDirectory("cafe").toFile();
        File file = new File(dir, "shift.txt");

        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            out.write("Latte");
            out.newLine();
            out.write("Mocha");
            out.newLine();
            out.write("Tea");
            out.newLine();
        }
        System.out.println("step 1: " + file.length());
        System.out.println("step 2: " + read(file));

        // Tham số true: ghi tiếp, giữ nội dung cũ
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8, true))) {
            out.write("Latte");
            out.newLine();
        }
        System.out.println("step 3: " + read(file));

        int lattes = 0;
        for (String line : read(file)) {
            if (line.equals("Latte")) {
                lattes++;
            }
        }
        System.out.println("step 4: " + lattes);

        List<String> kept = read(file);
        // Đọc trước và lọc trong bộ nhớ, vì mở luồng ghi đè sẽ xoá tệp ngay
        kept.removeIf(line -> line.equals("Latte"));
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            // Ghi lại toàn bộ danh sách đã lọc
            for (String line : kept) {
                out.write(line);
                out.newLine();
            }
        }
        System.out.println("step 5: " + read(file));

        file.delete();
        dir.delete();
    }
}
