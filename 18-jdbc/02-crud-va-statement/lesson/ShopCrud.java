import java.math.BigDecimal;
import java.sql.*;

public class ShopCrud {
    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "app_pw")) {
            try (PreparedStatement ps = c.prepareStatement("INSERT INTO product(sku, name, price) VALUES (?, ?, ?)")) {
                ps.setString(1, "MN-04");
                ps.setString(2, "Monitor");
                ps.setBigDecimal(3, new BigDecimal("120.00"));
                int rows = ps.executeUpdate();
                System.out.println("step 1: rows " + rows);
            }
            try (PreparedStatement ps = c.prepareStatement("SELECT price FROM product WHERE sku = ?")) {
                ps.setString(1, "MN-04");
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    System.out.println("step 2: " + rs.getBigDecimal("price"));
                }
            }
            try (PreparedStatement ps = c.prepareStatement("UPDATE product SET price = ? WHERE sku = ?")) {
                ps.setBigDecimal(1, new BigDecimal("99.90"));
                ps.setString(2, "MN-04");
                System.out.println("step 3: updated " + ps.executeUpdate());
            }
            try (PreparedStatement ps = c.prepareStatement("DELETE FROM product WHERE sku = ?")) {
                ps.setString(1, "MN-04");
                // lần một xoá được một hàng, lần hai không còn hàng nào khớp
                int first = ps.executeUpdate();
                int second = ps.executeUpdate();
                System.out.println("step 4: deleted " + first + " then " + second);
            }
        }
    }
}
