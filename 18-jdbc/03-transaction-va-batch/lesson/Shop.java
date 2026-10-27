import java.math.BigDecimal;
import java.sql.*;

public class Shop {
    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "app_pw")) {
            // tắt tự commit để các lệnh sau nằm trong một giao dịch
            c.setAutoCommit(false);
            System.out.println("step 1: autocommit " + c.getAutoCommit());
            try (PreparedStatement ps = c.prepareStatement("UPDATE account SET balance = balance + ? WHERE id = ?")) {
                ps.setBigDecimal(1, new BigDecimal("40.00"));
                ps.setLong(2, 2);
                ps.executeUpdate();
                ps.setBigDecimal(1, new BigDecimal("-40.00"));
                ps.setLong(2, 1);
                ps.executeUpdate();
                c.commit();
                System.out.println("step 2: committed");
                ps.setBigDecimal(1, new BigDecimal("500.00"));
                ps.setLong(2, 2);
                ps.executeUpdate();
                try {
                    ps.setBigDecimal(1, new BigDecimal("-500.00"));
                    ps.setLong(2, 1);
                    ps.executeUpdate();
                    c.commit();
                } catch (SQLException e) {
                    c.rollback();
                    System.out.println("step 3: rolled back");
                }
            }
            try (PreparedStatement ps = c.prepareStatement("INSERT INTO product(sku, name, price) VALUES (?, ?, 5.00)")) {
                for (String sku : new String[]{"B-1", "B-2", "B-3"}) {
                    ps.setString(1, sku);
                    ps.setString(2, "Item " + sku);
                    ps.addBatch();
                }
                int[] counts = ps.executeBatch();
                c.commit();
                System.out.println("step 4: counts " + counts.length);
            }
        }
    }
}
