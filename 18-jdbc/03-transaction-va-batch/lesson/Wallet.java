import java.math.BigDecimal;
import java.sql.*;

public class Wallet {
    static boolean transfer(Connection c, long from, long to, BigDecimal amount) throws SQLException {
        c.setAutoCommit(false);
        try (PreparedStatement ps = c.prepareStatement("UPDATE account SET balance = balance + ? WHERE id = ?")) {
            ps.setBigDecimal(1, amount);
            ps.setLong(2, to);
            ps.executeUpdate();
            ps.setBigDecimal(1, amount.negate());
            ps.setLong(2, from);
            ps.executeUpdate();
            c.commit();
            return true;
        } catch (SQLException e) {
            // CHECK từ chối vì thiếu tiền thì huỷ cả hai lệnh và báo false
            c.rollback();
            return false;
        }
    }

    static BigDecimal balance(Connection c, long id) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement("SELECT balance FROM account WHERE id = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getBigDecimal(1);
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "app_pw")) {
            for (String amount : new String[]{"30", "500", "20"}) {
                System.out.println("transfer " + amount + ": " + transfer(c, 1, 2, new BigDecimal(amount)));
            }
            System.out.println("An=" + balance(c, 1) + " Binh=" + balance(c, 2));
        }
    }
}
