import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class OrderLog {
    public static void main(String[] args) throws IOException {
        File dir = Files.createTempDirectory("cafe").toFile(); // thư mục tạm, không đụng tệp thật
        File file = new File(dir, "orders.txt"); // mới chỉ là một cái tên, chưa có tệp
        // try mở luồng trong ngoặc: hết khối thì close() tự chạy
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            out.write("Latte");
            out.newLine(); // xuống dòng để mỗi đơn một dòng
            out.write("Mocha");
            out.newLine();
            out.write("Tea");
            out.newLine();
        }
        System.out.println("exists " + file.exists()); // close đã đẩy chữ xuống tệp
        try (BufferedReader in = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            int number = 1;
            while ((line = in.readLine()) != null) { // mỗi vòng gọi readLine đúng một lần
                System.out.println(number + ": " + line);
                number++;
            }
        }
        System.out.println("deleted " + (file.delete() && dir.delete())); // xoá tệp trước, rồi mới xoá thư mục
    }
}
