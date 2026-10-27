import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class PriceDesk {
    static BufferedReader reader(Socket s) throws IOException {
        return new BufferedReader(new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8));
    }
    static PrintWriter writer(Socket s) throws IOException {
        return new PrintWriter(s.getOutputStream(), true, StandardCharsets.UTF_8); // true: tự flush sau println
    }

    static void serveOne(ServerSocket server) {
        try (Socket client = server.accept()) { // đứng chờ tới khi có khách nối vào
            String request = reader(client).readLine(); // chờ tới khi nhận đủ một dòng
            System.out.println("server got: " + request);
            writer(client).println(request.equals("PRICE Latte") ? "Latte 45000" : "UNKNOWN");
        } catch (IOException e) {
            System.out.println("server error: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        try (ServerSocket server = new ServerSocket(0, 50, InetAddress.getLoopbackAddress())) { // cổng 0: máy tự chọn
            Thread desk = new Thread(() -> serveOne(server)); // máy chủ chạy ở luồng riêng
            desk.start();
            try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), server.getLocalPort())) {
                writer(socket).println("PRICE Latte"); // println kết thúc tin nhắn bằng xuống dòng
                System.out.println("client got: " + reader(socket).readLine());
            }
            desk.join(); // chờ luồng máy chủ xong rồi mới đóng ServerSocket
        }
    }
}
