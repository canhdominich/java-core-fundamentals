import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.*;
import java.net.http.*;
import java.nio.charset.StandardCharsets;

public class Breakages {
    public static void main(String[] args) throws Exception {
        InetAddress loopback = InetAddress.getLoopbackAddress();

        // Lỗi 1: nối vào cổng không ai lắng nghe
        int closedPort;
        try (ServerSocket temp = new ServerSocket(0, 50, loopback)) {
            closedPort = temp.getLocalPort();
        }
        try (Socket socket = new Socket(loopback, closedPort)) {
            System.out.println("connected");
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        // Lỗi 2: hai máy chủ cùng xin một cổng
        try (ServerSocket first = new ServerSocket(0, 50, loopback)) {
            try (ServerSocket second = new ServerSocket(first.getLocalPort(), 50, loopback)) {
                System.out.println("second server started");
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }

        // Lỗi 3: máy chủ trả lời nhưng không flush nên khách không nhận được gì
        try (ServerSocket server = new ServerSocket(0, 50, loopback)) {
            Thread desk = new Thread(() -> {
                try (Socket client = server.accept()) {
                    new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8)).readLine();
                    PrintWriter out = new PrintWriter(client.getOutputStream(), false, StandardCharsets.UTF_8);
                    out.println("Latte 45000");
                    Thread.sleep(800);
                } catch (IOException | InterruptedException ignored) {
                }
            });
            desk.start();
            try (Socket socket = new Socket(loopback, server.getLocalPort())) {
                socket.setSoTimeout(300);
                new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8).println("PRICE Latte");
                try {
                    System.out.println(new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)).readLine());
                } catch (SocketTimeoutException e) {
                    System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                }
            }
            desk.join();
        }

        // Lỗi 4: mã 404 không phải ngoại lệ
        HttpServer http = HttpServer.create(new InetSocketAddress(loopback, 0), 0);
        http.createContext("/", exchange -> {
            exchange.sendResponseHeaders(404, -1);
            exchange.close();
        });
        http.start();
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create("http://127.0.0.1:" + http.getAddress().getPort() + "/price/Cocoa")).GET().build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("status " + response.statusCode() + ", no exception was thrown");
        } finally {
            http.stop(0);
        }
    }
}
