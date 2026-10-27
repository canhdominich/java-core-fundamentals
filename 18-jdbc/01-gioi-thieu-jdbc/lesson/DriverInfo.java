import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverInfo {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "app_pw")) {
            // DatabaseMetaData cho biết thông tin driver và máy chủ
            DatabaseMetaData meta = connection.getMetaData();
            System.out.println(meta.getDriverName() + " " + meta.getDriverVersion().split(" ")[0]);
            System.out.println("JDBC " + meta.getJDBCMajorVersion() + "." + meta.getJDBCMinorVersion());
            System.out.println("batch updates " + meta.supportsBatchUpdates());
        }
    }
}
