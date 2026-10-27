import java.sql.*;
import java.util.*;

public class Extras {
    static final String URL = "jdbc:mysql://localhost:3307/shop";

    static int update(Connection c, String sql) throws SQLException {
        try (Statement st = c.createStatement()) {
            return st.executeUpdate(sql);
        }
    }

    static List<String> names(Connection c, String sql, String param) throws SQLException {
        List<String> out = new ArrayList<>();
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            if (param != null) ps.setString(1, param);
            try (ResultSet rs = ps.executeQuery()) { while (rs.next()) out.add(rs.getString(1)); }
        }
        return out;
    }

    public static void main(String[] args) throws SQLException {
        for (String opt : new String[]{"", "?useAffectedRows=true"}) {
            try (Connection c = DriverManager.getConnection(URL + opt, "app", "app_pw")) {
                int same = update(c, "UPDATE product SET stock = 12 WHERE id = 1");
                int changed = update(c, "UPDATE product SET stock = 13 WHERE id = 1");
                int none = update(c, "UPDATE product SET stock = 5 WHERE id = 99");
                System.out.println("AFFECTED " + (opt.isEmpty() ? "default" : opt) + ": same value " + same + ", new value " + changed + ", no row " + none);
                update(c, "UPDATE product SET stock = 12 WHERE id = 1");
            }
        }
        try (Connection c = DriverManager.getConnection(URL, "app", "app_pw")) {
            System.out.println("ORDER BY ?     : " + names(c, "SELECT name FROM product ORDER BY ?", "price"));
            System.out.println("ORDER BY price : " + names(c, "SELECT name FROM product ORDER BY price", null));
        }
        for (String opt : new String[]{"", "?allowMultiQueries=true"}) {
            try (Connection c = DriverManager.getConnection(URL + opt, "app", "app_pw")) {
                update(c, "DROP TABLE IF EXISTS audit_log");
                update(c, "CREATE TABLE audit_log (id INT PRIMARY KEY)");
                String input = "x'; DROP TABLE audit_log; -- ";
                String outcome;
                try (Statement st = c.createStatement()) {
                    st.execute("SELECT role FROM app_user WHERE username = '" + input + "'");
                    outcome = "ran";
                } catch (SQLException e) {
                    outcome = "rejected " + e.getClass().getSimpleName() + " " + e.getSQLState();
                }
                int left = 0;
                try (Statement st = c.createStatement(); ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'shop' AND table_name = 'audit_log'")) {
                    rs.next();
                    left = rs.getInt(1);
                }
                System.out.println("STACKED " + (opt.isEmpty() ? "default" : opt) + ": " + outcome + ", audit_log tables left " + left);
                update(c, "DROP TABLE IF EXISTS audit_log");
            }
        }
    }
}
