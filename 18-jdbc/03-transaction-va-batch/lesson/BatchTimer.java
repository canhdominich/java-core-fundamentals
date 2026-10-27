import java.sql.*;

public class BatchTimer {
    static final int ROWS = 1000;
    static final String URL = "jdbc:mysql://localhost:3307/shop";

    static long run(String options, boolean tx, boolean batch) throws SQLException {
        try (Connection c = DriverManager.getConnection(URL + options, "app", "app_pw")) {
            try (Statement st = c.createStatement()) {
                st.executeUpdate("TRUNCATE TABLE product");
            }
            long start = System.nanoTime();
            c.setAutoCommit(!tx);
            try (PreparedStatement ps = c.prepareStatement("INSERT INTO product(sku, name, price) VALUES (?, ?, 5.00)")) {
                for (int i = 0; i < ROWS; i++) {
                    ps.setString(1, "S" + i);
                    ps.setString(2, "Item " + i);
                    if (batch) {
                        ps.addBatch();
                    } else {
                        ps.executeUpdate();
                    }
                }
                if (batch) {
                    ps.executeBatch();
                }
            }
            if (tx) {
                c.commit();
            }
            return (System.nanoTime() - start) / 1_000_000;
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("autocommit, one statement per row : " + run("", false, false) + " ms");
        System.out.println("one transaction, one statement per row : " + run("", true, false) + " ms");
        System.out.println("one transaction, batch : " + run("", true, true) + " ms");
        System.out.println("one transaction, batch, rewriteBatchedStatements : " + run("?rewriteBatchedStatements=true", true, true) + " ms");
    }
}
