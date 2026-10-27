import java.math.BigDecimal;
import java.sql.*;

public class Transfer {
    static void move(Connection c, BigDecimal amount) throws SQLException {
        // từ đây các lệnh chỉ nằm trong giao dịch, chưa được ghi thật
        c.setAutoCommit(false);
        try (PreparedStatement ps = c.prepareStatement("UPDATE account SET balance = balance + ? WHERE id = ?")) {
            ps.setBigDecimal(1, amount);
            ps.setLong(2, 2);
            ps.executeUpdate();
            ps.setBigDecimal(1, amount.negate());
            ps.setLong(2, 1);
            // lệnh trừ tiền của An có thể bị CHECK từ chối, ném SQLException
            ps.executeUpdate();
            // đến đây cả hai lệnh mới được ghi thật
            c.commit();
        } catch (SQLException e) {
            // huỷ cả lệnh cộng tiền đã chạy trước đó
            c.rollback();
            throw e;
        }
    }

    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "app_pw")) {
            for (String amount : new String[]{"30.00", "500.00"}) {
                try {
                    move(c, new BigDecimal(amount));
                    System.out.println(amount + " committed");
                } catch (SQLException e) {
                    System.out.println(amount + " rolled back, SQLState " + e.getSQLState());
                }
            }
        }
    }
}
