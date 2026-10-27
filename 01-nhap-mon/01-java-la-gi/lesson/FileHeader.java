import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class FileHeader {
    public static void main(String[] args) throws IOException {
        // Đọc file .class có đường dẫn truyền qua dòng lệnh
        try (DataInputStream in = new DataInputStream(new FileInputStream(args[0]))) {
            // Bỏ qua magic và minor
            in.readInt();
            in.readUnsignedShort();
            System.out.println("major = " + in.readUnsignedShort());
        }
    }
}
