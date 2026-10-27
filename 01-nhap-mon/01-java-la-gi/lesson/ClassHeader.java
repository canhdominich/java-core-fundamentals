import java.io.DataInputStream;
import java.io.IOException;

// Đọc tám byte đầu của file String.class nằm sẵn trong JDK
public class ClassHeader {
    public static void main(String[] args) throws IOException {
        // try có ngoặc tròn: luồng tự đóng khi khối kết thúc
        try (DataInputStream in = new DataInputStream(String.class.getResourceAsStream("String.class"))) {
            // 4 byte đầu: số nhận dạng, luôn là CAFEBABE
            int magic = in.readInt();
            // 2 byte kế: phiên bản phụ
            int minor = in.readUnsignedShort();
            // 2 byte kế nữa: phiên bản chính, thứ tự đọc phải đúng thứ tự trong file
            int major = in.readUnsignedShort();
            System.out.println("magic = " + Integer.toHexString(magic).toUpperCase());
            System.out.println("minor = " + minor);
            System.out.println("major = " + major);
            // Từ Java 5 trở đi: số Java bằng major trừ 44
            System.out.println("java  = " + (major - 44));
        }
    }
}
