import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Buffering {
    public static void main(String[] args) throws IOException {
        File dir = Files.createTempDirectory("cafe").toFile();
        File file = new File(dir, "orders.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
        // write chỉ đưa chữ vào bộ đệm trong bộ nhớ
        out.write("hello");
        System.out.println("after write: " + file.length());
        // flush đẩy bộ đệm xuống tệp ngay
        out.flush();
        System.out.println("after flush: " + file.length());
        out.write("!");
        // close tự flush phần còn lại rồi đóng luồng
        out.close();
        System.out.println("after close: " + file.length());
        try {
            // Luồng đã đóng nên dòng này ném IOException
            out.write("more");
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        file.delete();
        dir.delete();
    }
}
