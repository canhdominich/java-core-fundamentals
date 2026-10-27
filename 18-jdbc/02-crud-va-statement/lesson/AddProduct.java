import java.math.BigDecimal;
import java.sql.*;

public class AddProduct {
    public static void main(String[] args) throws SQLException {
        // không liệt kê cột id, để MySQL tự đặt
        String insert = "INSERT INTO product(sku, name, price) VALUES (?, ?, ?)";
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "app_pw");
             // cờ RETURN_GENERATED_KEYS cho phép lấy id mới sau khi chèn
             PreparedStatement ps = c.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, "HD-03");
            ps.setString(2, "Headset");
            ps.setBigDecimal(3, new BigDecimal("39.90"));
            // INSERT dùng executeUpdate, kết quả là số hàng bị ảnh hưởng
            int rows = ps.executeUpdate();
            long id;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                // ResultSet của khóa cũng cần next() trước khi đọc
                keys.next();
                id = keys.getLong(1);
            }
            System.out.println("inserted rows " + rows + ", new id " + id);
            try (PreparedStatement find = c.prepareStatement("SELECT name, price FROM product WHERE id = ?")) {
                find.setLong(1, id);
                try (ResultSet rs = find.executeQuery()) {
                    rs.next();
                    System.out.println("read back " + rs.getString("name") + " " + rs.getBigDecimal("price"));
                }
            }
        }
    }
}
