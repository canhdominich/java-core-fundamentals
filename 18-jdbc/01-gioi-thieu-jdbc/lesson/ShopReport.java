import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopReport {
    public static void main(String[] args) throws SQLException {
        // địa chỉ database: loại, máy, cổng, tên database
        String url = "jdbc:mysql://localhost:3307/shop";
        try (Connection connection = DriverManager.getConnection(url, "app", "app_pw")) {
            System.out.println("step 1: " + connection.getClass().getSimpleName());
            String sql = "SELECT sku, name, stock FROM product WHERE price >= ? ORDER BY price";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setBigDecimal(1, new BigDecimal("0"));
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("step 2: " + rs.getString("sku") + " " + rs.getString("name"));
                    }
                }
                ps.setBigDecimal(1, new BigDecimal("30"));
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("step 3: " + rs.getString("name"));
                    }
                }
                ps.setBigDecimal(1, new BigDecimal("0"));
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // getObject giữ nguyên NULL, còn getInt sẽ biến NULL thành 0
                        Integer stock = rs.getObject("stock", Integer.class);
                        System.out.println("step 4: " + rs.getString("name") + " " + (stock == null ? "unknown" : stock));
                    }
                }
            }
        }
    }
}
