import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstQuery {
    public static void main(String[] args) throws SQLException {
        // tiền tố jdbc:mysql: là thứ DriverManager dùng để chọn driver
        String url = "jdbc:mysql://localhost:3307/shop";
        // dấu ? là ô trống, giá trị được điền sau bằng setBigDecimal
        String sql = "SELECT sku, name, price FROM product WHERE price > ? ORDER BY price";
        // try-with-resources đóng ps trước rồi tới connection, kể cả khi có lỗi
        try (Connection connection = DriverManager.getConnection(url, "app", "app_pw");
             PreparedStatement ps = connection.prepareStatement(sql)) {
            System.out.println("statement class: " + ps.getClass().getName());
            // chỉ số ô trống bắt đầu từ 1, không phải từ 0
            ps.setBigDecimal(1, new BigDecimal("10.00"));
            try (ResultSet rs = ps.executeQuery()) {
                // next() trả false khi đã hết hàng
                while (rs.next()) {
                    System.out.println(rs.getString("sku") + " " + rs.getString("name") + " " + rs.getBigDecimal("price"));
                }
            }
        }
    }
}
