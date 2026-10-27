import java.io.DataInputStream;
import java.io.IOException;

public class PassportCheck {
    // Đổi số major trong file .class ra số Java
    static int javaOf(int major) {
        return major - 44;
    }

    // Major cao nhất mà JVM đang chạy hiểu được
    static int jvmLimit() {
        return Runtime.version().feature() + 44;
    }

    // File chạy được khi major của nó không lớn hơn giới hạn của JVM
    static boolean canRun(int fileMajor) {
        return fileMajor <= jvmLimit();
    }

    static String advice(int fileMajor) {
        if (canRun(fileMajor)) {
            return "runs here";
        }
        return "needs Java " + javaOf(fileMajor) + " or newer";
    }

    static int readMajor() throws IOException {
        try (DataInputStream in = new DataInputStream(String.class.getResourceAsStream("String.class"))) {
            // Đọc và bỏ qua magic và minor để tới đúng vị trí của major
            in.readInt();
            in.readUnsignedShort();
            return in.readUnsignedShort();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("step 1: " + javaOf(52) + " " + javaOf(55) + " " + javaOf(61));
        System.out.println("step 2: " + jvmLimit());
        System.out.println("step 3: " + canRun(52) + " " + canRun(65));
        System.out.println("step 4: " + advice(65));
        System.out.println("step 5: " + advice(readMajor()));
    }
}
