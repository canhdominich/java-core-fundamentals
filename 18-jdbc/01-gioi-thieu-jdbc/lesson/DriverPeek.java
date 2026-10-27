import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverPeek {
    public static void main(String[] args) {
        // driver đã được DriverManager nạp tự động từ file META-INF/services trong jar
        DriverManager.drivers().forEach(d -> System.out.println("registered: " + d.getClass().getName()));
        String[] urls = {"jdbc:mysql://localhost:3307/shop", "mysql://localhost:3307/shop"};
        for (String url : urls) {
            try {
                // getDriver chỉ hỏi driver nào nhận URL, chưa mở kết nối nào
                System.out.println(url + " : " + DriverManager.getDriver(url).getClass().getName());
            } catch (SQLException e) {
                System.out.println(url + " : " + e.getMessage() + " (SQLState " + e.getSQLState() + ")");
            }
        }
    }
}
