import java.util.ArrayList;
import java.util.List;

public class Oom {
    public static void main(String[] args) {
        List<byte[]> kept = new ArrayList<>();
        try {
            while (true) {
                // Giữ phiếu tới mọi mảng nên không mảng nào bị dọn
                kept.add(new byte[1024 * 1024]);
            }
        } catch (OutOfMemoryError e) {
            kept = null;
            System.out.println("caught: " + e.getMessage());
        }
    }
}
