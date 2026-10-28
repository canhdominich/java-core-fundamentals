import java.sql.*;
import java.util.Arrays;

public class BatchInsert {
    static void show(Connection c, String sql) throws SQLException {
        try (Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            // Hàm phụ chỉ để in bảng: mỗi hàng in hai cột đầu của câu SELECT
            while (rs.next()) {
                System.out.println("  " + rs.getString(1) + "  " + rs.getString(2));
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "app_pw");
             PreparedStatement ps = c.prepareStatement("INSERT INTO product(sku, name, price) VALUES (?, ?, 5.00)")) {
            // cả lô nằm trong một transaction, chỉ commit một lần
            c.setAutoCommit(false);
            for (String sku : new String[]{"A-1", "A-2", "A-3"}) {
                ps.setString(1, sku);
                ps.setString(2, "Item " + sku);
                // addBatch chỉ xếp dòng vào hàng chờ, chưa gửi gì
                ps.addBatch();
            }
            // executeBatch gửi cả lô và trả về một số cho mỗi dòng
            int[] counts = ps.executeBatch();
            c.commit();
            System.out.println("counts " + Arrays.toString(counts));
            show(c, "SELECT sku, name FROM product ORDER BY id");
        }
    }
}
