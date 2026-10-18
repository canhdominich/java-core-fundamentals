package dev.canhdominich.core.net.basics;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public final class Http {

    private Http() {
    }

    static HttpServer start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 0);
        server.createContext("/hello", ex -> reply(ex, 200, "Hello from the lab server"));
        server.createContext("/echo", ex -> {
            String body = new String(ex.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            reply(ex, 200, ex.getRequestMethod() + " received " + body.length() + " characters: " + body);
        });
        server.createContext("/slow", ex -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ignored) {
            }
            reply(ex, 200, "finally");
        });
        server.start();
        return server;
    }

    private static void reply(com.sun.net.httpserver.HttpExchange ex, int status, String text) throws IOException {
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().add("Content-Type", "text/plain; charset=utf-8");
        ex.sendResponseHeaders(status, bytes.length);
        try (OutputStream out = ex.getResponseBody()) {
            out.write(bytes);
        }
    }

    private static String base(HttpServer server) {
        return "http://127.0.0.1:" + server.getAddress().getPort();
    }

    public static String get() throws Exception {
        HttpServer server = start();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(base(server) + "/hello")).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return "status " + response.statusCode() + ", body '" + response.body() + "', content type " + response.headers().firstValue("content-type").orElse("none") + ", protocol " + response.version();
        } finally {
            server.stop(0);
        }
    }

    public static String notFoundIsNotAnException() throws Exception {
        HttpServer server = start();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(base(server) + "/missing")).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return "status " + response.statusCode() + " came back as a normal response, no exception was thrown";
        } finally {
            server.stop(0);
        }
    }

    public static String post() throws Exception {
        HttpServer server = start();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(base(server) + "/echo")).header("Content-Type", "text/plain; charset=utf-8").POST(HttpRequest.BodyPublishers.ofString("Xin chào")).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } finally {
            server.stop(0);
        }
    }

    public static String timeout() throws Exception {
        HttpServer server = start();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(base(server) + "/slow")).timeout(Duration.ofMillis(200)).GET().build();
            try {
                client.send(request, HttpResponse.BodyHandlers.ofString());
                return "answered";
            } catch (HttpTimeoutException e) {
                return e.getClass().getSimpleName() + ": " + e.getMessage();
            }
        } finally {
            server.stop(0);
        }
    }

    public static String asyncCalls() throws Exception {
        HttpServer server = start();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(base(server) + "/hello")).GET().build();
            CompletableFuture<String> first = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
            CompletableFuture<Integer> second = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::statusCode);
            return "two calls in flight together, first '" + first.get() + "', second status " + second.get();
        } finally {
            server.stop(0);
        }
    }

    public static String connectionRefused() throws Exception {
        HttpServer server = start();
        String url = base(server) + "/hello";
        server.stop(0);
        HttpClient client = HttpClient.newHttpClient();
        try {
            client.send(HttpRequest.newBuilder().uri(URI.create(url)).GET().build(), HttpResponse.BodyHandlers.ofString());
            return "answered";
        } catch (IOException e) {
            return "server gone: " + e.getClass().getSimpleName();
        }
    }
}
