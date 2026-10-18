package dev.canhdominich.core.net.basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class EchoServer implements AutoCloseable {

    private final ServerSocket server;
    private final ExecutorService pool;
    private final boolean concurrent;

    public EchoServer(boolean concurrent) throws IOException {
        this.server = new ServerSocket(0, 50, InetAddress.getLoopbackAddress());
        this.concurrent = concurrent;
        this.pool = Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r, "echo-worker");
            t.setDaemon(true);
            return t;
        });
        Thread acceptor = new Thread(this::acceptLoop, "echo-acceptor");
        acceptor.setDaemon(true);
        acceptor.start();
    }

    public int port() {
        return server.getLocalPort();
    }

    private void acceptLoop() {
        try {
            while (!server.isClosed()) {
                Socket client = server.accept();
                if (concurrent) {
                    pool.submit(() -> serve(client));
                } else {
                    serve(client);
                }
            }
        } catch (IOException ignored) {
        }
    }

    private void serve(Socket client) {
        try (client;
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true, StandardCharsets.UTF_8)) {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.equals("hold")) {
                    Thread.sleep(600);
                    out.println("released");
                } else {
                    out.println("Server received: " + line);
                }
            }
        } catch (IOException | InterruptedException ignored) {
        }
    }

    @Override
    public void close() throws IOException {
        server.close();
        pool.shutdownNow();
    }
}
