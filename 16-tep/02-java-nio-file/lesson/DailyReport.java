import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class DailyReport {
    public static void main(String[] args) throws IOException {
        Path root = Files.createTempDirectory("cafe"); // thư mục tạm, không đụng tệp thật
        Path month = root.resolve("2026-10"); // mới chỉ là một cái tên
        Files.createDirectories(month); // lệnh này mới tạo thư mục thật
        Path report = month.resolve("day1.txt"); // vẫn chỉ là tên, tệp chưa có
        Files.writeString(report, "Latte 12\nMocha 7\n"); // tạo tệp, ghi, đóng trong một lệnh
        List<String> lines = Files.readAllLines(report);
        System.out.println(lines);
        System.out.println(report.getFileName() + " in " + report.getParent().getFileName()); // chỉ xử lý chữ
        Path backup = root.resolve("day1.bak");
        Files.copy(report, backup); // lần hai sẽ ném FileAlreadyExistsException
        System.out.println(Files.size(backup) + " bytes, same text " + Files.readString(backup).equals(Files.readString(report)));
        Files.delete(backup); // xoá từ trong ra ngoài: thư mục phải rỗng mới xoá được
        Files.delete(report);
        Files.delete(month);
        Files.delete(root);
    }
}
