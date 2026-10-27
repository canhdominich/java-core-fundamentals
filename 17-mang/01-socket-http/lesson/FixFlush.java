import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class FixFlush {
    public static void main(String[] args) throws Exception {
        try (ServerSocket server = new ServerSocket(0, 50, InetAddress.getLoopbackAddress())) {
            Thread desk = new Thread(() -> {
                try (Socket client = server.accept()) {
                    new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8)).readLine();
                    PrintWriter out = new PrintWriter(client.getOutputStream(), false, StandardCharsets.UTF_8);
                    out.println("Latte 45000");
                    // flush bằng tay thay cho tham số tự flush
                    out.flush();
                } catch (IOException e) {
                    System.out.println("server error: " + e.getMessage());
                }
            });
            desk.start();
            try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), server.getLocalPort())) {
                socket.setSoTimeout(1000);
                new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8).println("PRICE Latte");
                System.out.println(new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)).readLine());
            }
            desk.join();
        }
    }
}
