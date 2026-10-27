import java.sql.*;
import java.util.Arrays;

public class ErrorTour {
    static final String URL = "jdbc:mysql://localhost:3307/shop";

    static String count(Connection c, String sql) throws SQLException {
        try (Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            rs.next();
            return rs.getString(1);
        }
    }

    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection(URL, "app", "app_pw")) {
            c.setAutoCommit(false);
            try (Statement st = c.createStatement()) {
                st.executeUpdate("UPDATE account SET balance = balance + 10 WHERE id = 2");
            }
        }
        try (Connection c = DriverManager.getConnection(URL, "app", "app_pw")) {
            System.out.println("A forgot commit: Binh = " + count(c, "SELECT balance FROM account WHERE id = 2"));
            try (Statement st = c.createStatement()) {
                st.executeUpdate("UPDATE account SET balance = balance - 999 WHERE id = 1");
            } catch (SQLException e) {
                System.out.println("B check | " + e.getClass().getSimpleName() + " | " + e.getSQLState() + " | " + e.getErrorCode() + " | " + e.getMessage());
            }
            try {
                c.rollback();
            } catch (SQLException e) {
                System.out.println("C rollback in autocommit | " + e.getClass().getSimpleName() + " | " + e.getSQLState() + " | " + e.getMessage());
            }
            try (PreparedStatement ps = c.prepareStatement("INSERT INTO product(sku, name, price) VALUES (?, 'x', 1)")) {
                for (String sku : new String[]{"D-1", "D-2", "D-2", "D-3"}) {
                    ps.setString(1, sku);
                    ps.addBatch();
                }
                ps.executeBatch();
            } catch (BatchUpdateException e) {
                System.out.println("D batch | " + e.getClass().getSimpleName() + " | " + e.getSQLState() + " | " + Arrays.toString(e.getUpdateCounts()) + " | " + e.getMessage());
                System.out.println("D rows stored: " + count(c, "SELECT COUNT(*) FROM product"));
            }
        }
    }
}
