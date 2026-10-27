import java.net.URI;

public class UriParts {
    public static void main(String[] args) {
        URI uri = URI.create("http://localhost:8080/menu/drinks?type=hot&size=2");
        System.out.println("scheme " + uri.getScheme());
        System.out.println("host " + uri.getHost() + ", port " + uri.getPort());
        System.out.println("path " + uri.getPath());
        System.out.println("query " + uri.getQuery());
        // cổng không được ghi thì getPort trả về -1
        System.out.println("port when not written: " + URI.create("http://localhost/menu").getPort());
    }
}
