import java.sql.*;
import java.util.*;

public class Search {
    static List<String> searchByName(Connection c, String part) throws SQLException {
        List<String> names = new ArrayList<>();
        try (PreparedStatement ps = c.prepareStatement("SELECT name FROM product WHERE name LIKE ? ORDER BY name")) {
            // dấu % được thêm ở phía Java, cả mẫu đi vào ô trống như một giá trị
            ps.setString(1, "%" + part + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    names.add(rs.getString("name"));
                }
            }
        }
        return names;
    }

    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "app_pw")) {
            System.out.println(searchByName(c, "key"));
            System.out.println(searchByName(c, "o"));
            System.out.println(searchByName(c, "x' OR '1'='1"));
        }
    }
}
