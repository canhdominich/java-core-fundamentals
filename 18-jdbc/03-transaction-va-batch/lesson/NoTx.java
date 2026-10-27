import java.sql.*;

public class NoTx {
    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "app_pw");
             Statement st = c.createStatement()) {
            // autocommit đang bật nên lệnh cộng tiền này được ghi thật ngay
            st.executeUpdate("UPDATE account SET balance = balance + 500 WHERE id = 2");
            try {
                // lệnh trừ tiền bị CHECK từ chối, nhưng lệnh trên đã không thể quay lại
                st.executeUpdate("UPDATE account SET balance = balance - 500 WHERE id = 1");
            } catch (SQLException e) {
                System.out.println("step 2 failed, SQLState " + e.getSQLState());
            }
        }
    }
}
