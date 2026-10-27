import java.sql.*;
import java.util.List;

public class ImportProducts {
    static int importAll(Connection c, List<String> skus) throws SQLException {
        c.setAutoCommit(false);
        try (PreparedStatement ps = c.prepareStatement("INSERT INTO product(sku, name, price) VALUES (?, ?, 5.00)")) {
            for (String sku : skus) {
                ps.setString(1, sku);
                ps.setString(2, "Item " + sku);
                ps.addBatch();
            }
            int[] counts = ps.executeBatch();
            c.commit();
            return counts.length;
        } catch (BatchUpdateException e) {
            // batch lỗi một dòng thì huỷ cả giao dịch, không để lại dòng nào
            c.rollback();
            return 0;
        }
    }

    static String rows(Connection c) throws SQLException {
        try (Statement st = c.createStatement(); ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM product")) {
            rs.next();
            return rs.getString(1);
        }
    }

    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/shop", "app", "app_pw")) {
            System.out.println("imported " + importAll(c, List.of("N-1", "N-2", "N-3")));
            System.out.println("imported " + importAll(c, List.of("N-4", "N-1", "N-5")));
            System.out.println("rows " + rows(c));
        }
    }
}
