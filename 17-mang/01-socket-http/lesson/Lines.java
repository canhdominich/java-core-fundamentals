import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Lines {
    public static void main(String[] args) throws Exception {
        try (ServerSocket server = new ServerSocket(0, 50, InetAddress.getLoopbackAddress())) {
            Thread desk = new Thread(() -> {
                try (Socket client = server.accept()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true, StandardCharsets.UTF_8);
                    // readLine không trả về cho tới khi nhận được ký tự xuống dòng
                    out.println("Server received: " + in.readLine());
                } catch (IOException e) {
                    System.out.println("server error: " + e.getMessage());
                }
            });
            desk.start();
            try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), server.getLocalPort())) {
                // chờ đọc tối đa 300 mili giây, quá thì ném SocketTimeoutException
                socket.setSoTimeout(300);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                OutputStream out = socket.getOutputStream();
                // gửi chữ nhưng chưa có ký tự xuống dòng
                out.write("PRICE Latte".getBytes(StandardCharsets.UTF_8));
                out.flush();
                try {
                    System.out.println(in.readLine());
                } catch (SocketTimeoutException e) {
                    System.out.println("no newline yet: " + e.getClass().getSimpleName() + ": " + e.getMessage());
                }
                // ký tự xuống dòng đến, máy chủ mới coi tin nhắn là đủ
                out.write("\n".getBytes(StandardCharsets.UTF_8));
                out.flush();
                System.out.println("after the newline: " + in.readLine());
            }
            desk.join();
        }
    }
}
