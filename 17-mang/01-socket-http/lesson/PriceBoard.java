import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PriceBoard {
    static BufferedReader reader(Socket s) throws IOException {
        return new BufferedReader(new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8));
    }

    static PrintWriter writer(Socket s) throws IOException {
        return new PrintWriter(s.getOutputStream(), true, StandardCharsets.UTF_8);
    }

    static String reply(String request) {
        Map<String, Integer> prices = Map.of("Latte", 45000, "Mocha", 50000, "Tea", 30000);
        String drink = request.substring("PRICE ".length());
        if (prices.containsKey(drink)) {
            return drink + " " + prices.get(drink);
        }
        return "UNKNOWN " + drink;
    }

    static void serve(ServerSocket server) {
        try (Socket client = server.accept()) {
            BufferedReader in = reader(client);
            PrintWriter out = writer(client);
            int served = 0;
            String line;
            // vòng lặp dừng khi khách đóng kết nối và readLine trả về null
            while ((line = in.readLine()) != null) {
                out.println(reply(line));
                served++;
            }
            System.out.println("step 2: served " + served);
        } catch (IOException e) {
            System.out.println("server error: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("step 1: " + reply("PRICE Latte") + " | " + reply("PRICE Cocoa"));
        try (ServerSocket server = new ServerSocket(0, 50, InetAddress.getLoopbackAddress())) {
            Thread desk = new Thread(() -> serve(server));
            desk.start();
            try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), server.getLocalPort())) {
                BufferedReader in = reader(socket);
                PrintWriter out = writer(socket);
                List<String> replies = new ArrayList<>();
                for (String drink : new String[] {"Latte", "Tea", "Cocoa"}) {
                    out.println("PRICE " + drink);
                    replies.add(in.readLine());
                }
                System.out.println("step 3: " + replies);
            }
            desk.join();
        }

        int closedPort;
        try (ServerSocket temp = new ServerSocket(0, 50, InetAddress.getLoopbackAddress())) {
            closedPort = temp.getLocalPort();
        }
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), closedPort)) {
            System.out.println("step 4: connected");
        } catch (IOException e) {
            System.out.println("step 4: " + e.getClass().getSimpleName());
        }
    }
}
