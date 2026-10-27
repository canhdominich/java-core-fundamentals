import java.sql.*;

public class LoginDemo {
    static String unsafe(Connection c, String user, String pass) throws SQLException {
        // cách sai: dán nguyên văn dữ liệu người dùng vào câu SQL
        String sql = "SELECT role FROM app_user WHERE username = '" + user + "' AND password_hash = '" + pass + "'";
        try (Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            return rs.next() ? "in as " + rs.getString(1) : "denied";
        }
    }

    static String safe(Connection c, String user, String pass) throws SQLException {
        // cách đúng: câu SQL cố định, dữ liệu đi qua hai ô trống
        try (PreparedStatement ps = c.prepareStatement("SELECT role FROM app_user WHERE username = ? AND password_hash = ?")) {
            ps.setString(1, user);
            ps.setString(2, pass);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? "in as " + rs.getString(1) : "denied";
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        String[][] tries = {{"an", "hash-an"}, {"an", "wrong"}, {"admin' -- ", "anything"}};
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "app_pw")) {
            for (String[] t : tries) {
                System.out.println("user [" + t[0] + "] pass [" + t[1] + "] unsafe: " + unsafe(c, t[0], t[1]) + ", safe: " + safe(c, t[0], t[1]));
            }
        }
    }
}
