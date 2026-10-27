import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class PriceHttp {
    static final Map<String, Integer> PRICES = Map.of("Latte", 45000, "Mocha", 50000, "Tea", 30000);

    static HttpServer startServer() throws IOException {
        // cổng 0: hệ điều hành tự chọn một cổng đang rảnh
        HttpServer server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 0);
        server.createContext("/price/", exchange -> {
            String drink = exchange.getRequestURI().getPath().substring("/price/".length());
            Integer price = PRICES.get(drink);
            byte[] body = (price == null ? "unknown drink" : String.valueOf(price)).getBytes(StandardCharsets.UTF_8);
            // mã 404 báo "không tìm thấy", mã 200 báo thành công
            exchange.sendResponseHeaders(price == null ? 404 : 200, body.length);
            try (OutputStream out = exchange.getResponseBody()) {
                out.write(body);
            }
        });
        server.start();
        return server;
    }

    public static void main(String[] args) throws Exception {
        HttpServer server = startServer();
        try {
            HttpClient client = HttpClient.newHttpClient();
            String base = "http://127.0.0.1:" + server.getAddress().getPort();
            for (String drink : new String[] {"Latte", "Cocoa"}) {
                HttpRequest request = HttpRequest.newBuilder(URI.create(base + "/price/" + drink)).GET().build();
                // 404 vẫn là phản hồi bình thường, send không ném ngoại lệ
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("GET /price/" + drink + ": " + response.statusCode() + " " + response.body());
            }
        } finally {
            server.stop(0); // luôn dừng máy chủ để chương trình thoát
        }
    }
}
