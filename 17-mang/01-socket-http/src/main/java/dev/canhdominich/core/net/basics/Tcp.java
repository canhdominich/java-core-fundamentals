package dev.canhdominich.core.net.basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

public final class Tcp {

    private Tcp() {
    }

    static String ask(int port, String message, int timeoutMillis) throws IOException {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), port)) {
            socket.setSoTimeout(timeoutMillis);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            out.println(message);
            return in.readLine();
        }
    }

    public static String echo() throws Exception {
        try (EchoServer server = new EchoServer(false)) {
            return ask(server.port(), "Hello server", 2000);
        }
    }

    public static String messagesAreLines() throws Exception {
        try (EchoServer server = new EchoServer(false);
                Socket socket = new Socket(InetAddress.getLoopbackAddress(), server.port())) {
            socket.setSoTimeout(300);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            socket.getOutputStream().write("no newline yet".getBytes(StandardCharsets.UTF_8));
            socket.getOutputStream().flush();
            String first;
            try {
                first = in.readLine();
            } catch (SocketTimeoutException e) {
                first = "SocketTimeoutException: " + e.getMessage();
            }
            socket.getOutputStream().write("\n".getBytes(StandardCharsets.UTF_8));
            socket.getOutputStream().flush();
            String second = in.readLine();
            return "before the newline arrived: " + first + ", after it: " + second;
        }
    }

    public static String connectionRefused() throws IOException {
        int closedPort;
        try (ServerSocket probe = new ServerSocket(0, 1, InetAddress.getLoopbackAddress())) {
            closedPort = probe.getLocalPort();
        }
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), closedPort)) {
            return "connected";
        } catch (ConnectException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }

    public static String portAlreadyInUse() throws IOException {
        try (ServerSocket first = new ServerSocket(0, 1, InetAddress.getLoopbackAddress())) {
            try (ServerSocket second = new ServerSocket(first.getLocalPort(), 1, InetAddress.getLoopbackAddress())) {
                return "second bind accepted";
            } catch (BindException e) {
                return e.getClass().getSimpleName() + ": " + e.getMessage();
            }
        }
    }

    public static String oneClientAtATime() throws Exception {
        try (EchoServer server = new EchoServer(false)) {
            return secondClientResult(server);
        }
    }

    public static String threadPerClient() throws Exception {
        try (EchoServer server = new EchoServer(true)) {
            return secondClientResult(server);
        }
    }

    private static String secondClientResult(EchoServer server) throws Exception {
        Thread slow = new Thread(() -> {
            try {
                ask(server.port(), "hold", 3000);
            } catch (IOException ignored) {
            }
        });
        slow.setDaemon(true);
        slow.start();
        Thread.sleep(100);
        String reply;
        try {
            reply = ask(server.port(), "quick question", 300);
        } catch (SocketTimeoutException e) {
            reply = "no answer within 300 ms (" + e.getClass().getSimpleName() + ")";
        }
        slow.join(3000);
        return "second client got: " + reply;
    }
}
