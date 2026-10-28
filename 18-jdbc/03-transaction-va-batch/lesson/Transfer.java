import java.math.BigDecimal;
import java.sql.*;

public class Transfer {
    static void show(Connection c, String sql) throws SQLException {
        try (Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            // Hàm phụ chỉ để in bảng: mỗi hàng in hai cột đầu của câu SELECT
            while (rs.next()) {
                System.out.println("  " + rs.getString(1) + "  " + rs.getString(2));
            }
        }
    }

    static void add(Connection c, long id, BigDecimal delta) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement("UPDATE account SET balance = balance + ? WHERE id = ?")) {
            ps.setBigDecimal(1, delta);
            ps.setLong(2, id);
            ps.executeUpdate();
        }
    }

    static void move(Connection c, BigDecimal amount) throws SQLException {
        // từ đây các lệnh chỉ nằm trong giao dịch, chưa được ghi thật
        c.setAutoCommit(false);
        try {
            add(c, 2, amount);
            // lệnh trừ tiền của An có thể bị CHECK từ chối, ném SQLException
            add(c, 1, amount.negate());
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
                show(c, "SELECT owner, balance FROM account ORDER BY id");
            }
        }
    }
}
