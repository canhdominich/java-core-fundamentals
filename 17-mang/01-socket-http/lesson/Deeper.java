import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Deeper {
    static void serve(Socket client) {
        try (client) {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true, StandardCharsets.UTF_8);
            String line = in.readLine();
            if (line.equals("hold")) {
                Thread.sleep(600);
            }
            out.println("Server received: " + line);
        } catch (IOException | InterruptedException ignored) {
        }
    }

    static String ask(int port, String message, int timeoutMillis) throws IOException {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), port)) {
            socket.setSoTimeout(timeoutMillis);
            new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8).println(message);
            return new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)).readLine();
        } catch (SocketTimeoutException e) {
            return "no answer within " + timeoutMillis + " ms";
        }
    }

    static void run(boolean concurrent) throws Exception {
        ExecutorService pool = Executors.newCachedThreadPool();
        try (ServerSocket server = new ServerSocket(0, 50, InetAddress.getLoopbackAddress())) {
            Thread acceptor = new Thread(() -> {
                try {
                    while (!server.isClosed()) {
                        Socket client = server.accept();
                        // một luồng cho mỗi khách thì khách sau không phải chờ khách trước
                        if (concurrent) {
                            pool.submit(() -> serve(client));
                        } else {
                            serve(client);
                        }
                    }
                } catch (IOException ignored) {
                }
            });
            acceptor.setDaemon(true);
            acceptor.start();
            Thread slow = new Thread(() -> {
                try {
                    ask(server.getLocalPort(), "hold", 2000);
                } catch (IOException ignored) {
                }
            });
            slow.start();
            Thread.sleep(100);
            System.out.println((concurrent ? "thread per client: " : "one at a time: ") + ask(server.getLocalPort(), "quick question", 300));
            slow.join();
        } finally {
            pool.shutdownNow();
        }
    }

    public static void main(String[] args) throws Exception {
        run(false);
        run(true);
    }
}
