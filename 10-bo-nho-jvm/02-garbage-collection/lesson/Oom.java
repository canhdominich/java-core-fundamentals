import java.util.ArrayList;
import java.util.List;

public class Oom {
    static final List<byte[]> HISTORY = new ArrayList<>();

    public static void main(String[] args) {
        try {
            while (true) {
                // Danh sách static giữ mọi mảng nên không mảng nào được dọn
                HISTORY.add(new byte[1024 * 1024]);
            }
        } catch (OutOfMemoryError e) {
            int kept = HISTORY.size();
            HISTORY.clear();
            System.out.println("caught: " + e.getMessage() + ", history was not empty: " + (kept > 0));
        }
    }
}
