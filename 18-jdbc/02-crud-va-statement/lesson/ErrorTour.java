import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class ErrorTour {
    static void show(String label, Exception e) {
        System.out.println(label + " | " + e.getClass().getSimpleName() + " | " + (e instanceof SQLException s ? s.getSQLState() : "-") + " | " + e.getMessage().split("\n")[0]);
    }

    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "app_pw")) {
            try (PreparedStatement ps = c.prepareStatement("SELECT name FROM product WHERE id = ?")) {
                ps.executeQuery();
            } catch (SQLException e) { show("A no value", e); }
            try (PreparedStatement ps = c.prepareStatement("INSERT INTO product(sku, name, price) VALUES ('Z-1', 'Z', 1)")) {
                ps.executeQuery();
            } catch (SQLException e) { show("B executeQuery on INSERT", e); }
            try (PreparedStatement ps = c.prepareStatement("SELECT name FROM product")) {
                ps.executeUpdate();
            } catch (SQLException e) { show("C executeUpdate on SELECT", e); }
            try (PreparedStatement ps = c.prepareStatement("INSERT INTO product(sku, name, price) VALUES ('KB-01', 'dup', 1)")) {
                ps.executeUpdate();
            } catch (SQLException e) { show("D duplicate", e); }
            try (PreparedStatement ps = c.prepareStatement("INSERT INTO product(sku, name, price) VALUES ('Z-2', 'Z', 1)")) {
                ps.executeUpdate();
                ps.getGeneratedKeys();
                try (ResultSet k = ps.getGeneratedKeys()) { k.next(); k.getLong(1); }
            } catch (SQLException e) { show("E keys not requested", e); }
            try (PreparedStatement ps = c.prepareStatement("SELECT name FROM product ORDER BY ?")) {
                ps.setString(1, "price");
                List<String> names = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) { while (rs.next()) names.add(rs.getString(1)); }
                System.out.println("F ORDER BY ? with 'price': " + names);
            }
            try (PreparedStatement ps = c.prepareStatement("SELECT name FROM product ORDER BY price DESC")) {
                List<String> names = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) { while (rs.next()) names.add(rs.getString(1)); }
                System.out.println("F2 ORDER BY price DESC: " + names);
            }
            try (PreparedStatement ps = c.prepareStatement("SELECT name FROM product WHERE id = ?")) {
                ps.setLong(2, 1);
            } catch (SQLException e) { show("G index 2", e); }
        }
    }
}
