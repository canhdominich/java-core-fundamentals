package dev.canhdominich.core.net.basics;

import java.net.InetAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public final class Addresses {

    private Addresses() {
    }

    public static String loopback() throws UnknownHostException {
        InetAddress byName = InetAddress.getByName("localhost");
        InetAddress literal = InetAddress.getByName("192.168.1.10");
        return "localhost is " + byName.getHostAddress() + ", loopback " + byName.isLoopbackAddress() + ", 192.168.1.10 is a site local address " + literal.isSiteLocalAddress() + ", raw bytes " + literal.getAddress().length;
    }

    public static String uriParts() {
        URI uri = URI.create("https://shop.example.com:8443/orders/42?status=open&page=2#top");
        return "scheme " + uri.getScheme() + ", host " + uri.getHost() + ", port " + uri.getPort() + ", path " + uri.getPath() + ", query " + uri.getQuery() + ", fragment " + uri.getFragment();
    }

    public static String defaultPort() {
        URI uri = URI.create("https://shop.example.com/orders");
        return "no port written gives getPort " + uri.getPort();
    }

    public static String encoding() {
        String raw = "Xin chào & bạn=ok";
        String encoded = URLEncoder.encode(raw, StandardCharsets.UTF_8);
        String decoded = URLDecoder.decode(encoded, StandardCharsets.UTF_8);
        return "encoded " + encoded + ", decoded back equal " + decoded.equals(raw);
    }

    public static String badUri() {
        try {
            URI.create("http://example.com/a b");
            return "accepted";
        } catch (IllegalArgumentException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }

    public static String unknownHost() {
        try {
            InetAddress.getByName("host-that-does-not-exist.invalid");
            return "resolved";
        } catch (UnknownHostException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }
}
