import java.math.BigDecimal;
import java.sql.*;
import java.util.Optional;

public class ProductStore {
    static long insert(Connection c, String sku, String name, BigDecimal price) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement("INSERT INTO product(sku, name, price) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, sku);
            ps.setString(2, name);
            ps.setBigDecimal(3, price);
            ps.executeUpdate();
            // id do MySQL tự đặt nằm trong ResultSet khóa sinh ra
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
                return keys.getLong(1);
            }
        }
    }

    static Optional<String> findName(Connection c, long id) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement("SELECT name FROM product WHERE id = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                // Optional phân biệt "không tìm thấy" mà không cần null
                return rs.next() ? Optional.of(rs.getString("name")) : Optional.empty();
            }
        }
    }

    static int updatePrice(Connection c, long id, BigDecimal price) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement("UPDATE product SET price = ? WHERE id = ?")) {
            ps.setBigDecimal(1, price);
            ps.setLong(2, id);
            return ps.executeUpdate();
        }
    }

    static int delete(Connection c, long id) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement("DELETE FROM product WHERE id = ?")) {
            ps.setLong(1, id);
            return ps.executeUpdate();
        }
    }

    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "app_pw")) {
            long id = insert(c, "HD-03", "Headset", new BigDecimal("39.90"));
            System.out.println("id " + id);
            System.out.println(findName(c, id));
            System.out.println("update " + updatePrice(c, id, new BigDecimal("29.90")));
            System.out.println("delete " + delete(c, id));
            System.out.println("delete " + delete(c, id));
            System.out.println(findName(c, id));
        }
    }
}
