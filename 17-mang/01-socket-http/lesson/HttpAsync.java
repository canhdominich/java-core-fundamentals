import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class HttpAsync {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> prices = Map.of("Latte", 45000, "Tea", 30000);
        HttpServer server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 0);
        server.createContext("/price/", exchange -> {
            Integer price = prices.get(exchange.getRequestURI().getPath().substring("/price/".length()));
            byte[] body = (price == null ? "unknown drink" : String.valueOf(price)).getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(price == null ? 404 : 200, body.length);
            try (OutputStream out = exchange.getResponseBody()) {
                out.write(body);
            }
        });
        server.start();
        try {
            HttpClient client = HttpClient.newHttpClient();
            List<CompletableFuture<String>> calls = new ArrayList<>();
            for (String drink : new String[] {"Latte", "Tea", "Cocoa"}) {
                HttpRequest request = HttpRequest.newBuilder(URI.create("http://127.0.0.1:" + server.getAddress().getPort() + "/price/" + drink)).GET().build();
                // sendAsync trả về ngay, ba yêu cầu cùng bay trong khi chưa ai chờ
                calls.add(client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .thenApply(response -> drink + " " + response.statusCode() + " " + response.body()));
            }
            // join theo đúng thứ tự đã gửi nên kết quả in ra luôn cùng thứ tự
            for (CompletableFuture<String> call : calls) {
                System.out.println(call.join());
            }
        } finally {
            server.stop(0);
        }
    }
}
