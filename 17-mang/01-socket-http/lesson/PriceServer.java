import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class PriceServer implements AutoCloseable {
    private static final Map<String, Integer> PRICES = Map.of("Latte", 45000, "Mocha", 50000, "Tea", 30000);
    private final ServerSocket server;

    public PriceServer() throws IOException {
        server = new ServerSocket(0, 50, InetAddress.getLoopbackAddress());
        Thread acceptor = new Thread(this::acceptLoop);
        acceptor.setDaemon(true); // luồng nền không giữ chương trình lại khi main kết thúc
        acceptor.start();
    }

    public int port() {
        return server.getLocalPort();
    }

    private void acceptLoop() {
        try {
            while (!server.isClosed()) {
                try (Socket client = server.accept()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true, StandardCharsets.UTF_8);
                    String request = in.readLine();
                    String drink = request.substring("PRICE ".length());
                    out.println(PRICES.containsKey(drink) ? drink + " " + PRICES.get(drink) : "UNKNOWN " + drink);
                }
            }
        } catch (IOException ignored) {
            // close() đóng ServerSocket nên accept ném lỗi, đó là cách vòng lặp kết thúc
        }
    }

    public static String ask(int port, String request) throws IOException {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), port)) {
            socket.setSoTimeout(2000);
            new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8).println(request);
            return new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)).readLine();
        }
    }

    @Override
    public void close() throws IOException {
        server.close();
    }

    public static void main(String[] args) throws Exception {
        int port;
        try (PriceServer desk = new PriceServer()) {
            port = desk.port();
            System.out.println(ask(port, "PRICE Latte"));
            System.out.println(ask(port, "PRICE Cocoa"));
        }
        try {
            ask(port, "PRICE Latte");
        } catch (IOException e) {
            System.out.println("closed: " + e.getClass().getSimpleName());
        }
    }
}
