import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ErrorTour {
    static void show(String label, SQLException e) {
        System.out.println(label + " | " + e.getClass().getSimpleName() + " | SQLState " + e.getSQLState() + " | " + e.getMessage().split("\n")[0]);
    }

    public static void main(String[] args) throws SQLException {
        try {
            DriverManager.getConnection("mysql://localhost:3307/shop", "app", "app_pw");
        } catch (SQLException e) {
            show("A wrong prefix", e);
        }
        try {
            DriverManager.getConnection("jdbc:mysql://localhost:3399/shop", "app", "app_pw");
        } catch (SQLException e) {
            show("B nothing listening", e);
        }
        try {
            DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "wrong");
        } catch (SQLException e) {
            show("C wrong password", e);
        }
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "app_pw")) {
            try (PreparedStatement ps = c.prepareStatement("SELECT name FROM product WHERE sku = ?")) {
                ps.setString(0, "KB-01");
            } catch (SQLException e) {
                show("D index 0", e);
            }
            try (PreparedStatement ps = c.prepareStatement("SELECT name FROM product WHERE sku = ?")) {
                ps.setString(1, "KB-01");
                try (ResultSet rs = ps.executeQuery()) {
                    rs.getString("name");
                }
            } catch (SQLException e) {
                show("E no next", e);
            }
            try (PreparedStatement ps = c.prepareStatement("SELECT stock FROM product WHERE sku = 'MS-02'");
                 ResultSet rs = ps.executeQuery()) {
                rs.next();
                int stock = rs.getInt("stock");
                System.out.println("F getInt on NULL = " + stock + ", wasNull = " + rs.wasNull() + ", getObject = " + rs.getObject("stock", Integer.class));
            }
            try (PreparedStatement ps = c.prepareStatement("SELECT name FROM product WHERE sku = ?")) {
                ps.setString(1, "KB-01");
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    rs.getString("nme");
                }
            } catch (SQLException e) {
                show("G wrong column", e);
            }
        }
    }
}
