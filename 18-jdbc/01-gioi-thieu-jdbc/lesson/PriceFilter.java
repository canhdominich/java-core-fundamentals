import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriceFilter {
    static List<String> namesCheaperThan(Connection connection, BigDecimal max) throws SQLException {
        List<String> names = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT name FROM product WHERE price < ? ORDER BY price")) {
            // giá trị max đi qua ô trống, không nối chuỗi
            ps.setBigDecimal(1, max);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    names.add(rs.getString("name"));
                }
            }
        }
        return names;
    }

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "app_pw")) {
            System.out.println(namesCheaperThan(connection, new BigDecimal("20.00")));
            System.out.println(namesCheaperThan(connection, new BigDecimal("5.00")));
            System.out.println(namesCheaperThan(connection, new BigDecimal("100.00")));
        }
    }
}
