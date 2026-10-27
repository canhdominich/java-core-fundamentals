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

public class OrderStore {
    private final File file;

    public OrderStore(File file) {
        this.file = file;
    }

    public void append(String order) throws IOException {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8, true))) {
            out.write(order);
            out.newLine();
        }
    }

    public List<String> load() throws IOException {
        List<String> orders = new ArrayList<>();
        // Tệp chưa có thì coi như chưa có đơn nào
        if (!file.exists()) {
            return orders;
        }
        try (BufferedReader in = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = in.readLine()) != null) {
                orders.add(line);
            }
        }
        return orders;
    }

    public int remove(String order) throws IOException {
        // Đọc trước khi mở luồng ghi, vì ghi đè xoá nội dung cũ ngay lúc mở
        List<String> orders = load();
        int before = orders.size();
        orders.removeIf(o -> o.equals(order));
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            for (String o : orders) {
                out.write(o);
                out.newLine();
            }
        }
        // removeIf không cho biết số phần tử đã bỏ, nên so hai lần đếm
        return before - orders.size();
    }

    public static void main(String[] args) throws IOException {
        File dir = Files.createTempDirectory("cafe").toFile();
        OrderStore store = new OrderStore(new File(dir, "orders.txt"));
        System.out.println("empty: " + store.load());
        store.append("Latte");
        store.append("Mocha");
        store.append("Latte");
        System.out.println("loaded: " + store.load());
        System.out.println("removed Latte: " + store.remove("Latte") + " " + store.load());
        System.out.println("removed Cocoa: " + store.remove("Cocoa") + " " + store.load());
        new File(dir, "orders.txt").delete();
        dir.delete();
    }
}
