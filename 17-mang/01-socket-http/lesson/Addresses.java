import java.net.*;

public class Addresses {
    public static void main(String[] args) throws Exception {
        InetAddress loopback = InetAddress.getLoopbackAddress();
        System.out.println("loopback: " + loopback.getHostAddress() + ", isLoopback " + loopback.isLoopbackAddress());
        URI uri = URI.create("https://shop.example.com:8443/orders/42?status=open&page=2#top");
        System.out.println("scheme " + uri.getScheme() + ", host " + uri.getHost() + ", port " + uri.getPort());
        System.out.println("path " + uri.getPath() + ", query " + uri.getQuery() + ", fragment " + uri.getFragment());
        // không ghi cổng thì getPort trả về -1, cổng mặc định của https là 443
        System.out.println("no port written: " + URI.create("https://shop.example.com/menu").getPort());
        try (ServerSocket server = new ServerSocket(0, 50, loopback)) {
            // cổng 0 xin hệ điều hành một cổng rảnh, số cụ thể thay đổi mỗi lần chạy
            System.out.println("asked for port 0, got a real port: " + (server.getLocalPort() > 0));
        }
    }
}
