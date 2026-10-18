package dev.canhdominich.core.net.basics;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NetTest {

    @Test
    void serverEchoesALineOverTcp() throws Exception {
        assertThat(Tcp.echo()).isEqualTo("Server received: Hello server");
    }

    @Test
    void tcpIsAStreamSoALineEndsOnlyAtTheNewline() throws Exception {
        assertThat(Tcp.messagesAreLines()).isEqualTo("before the newline arrived: SocketTimeoutException: Read timed out, after it: Server received: no newline yet");
    }

    @Test
    void connectAndBindFailuresHaveSpecificExceptions() throws Exception {
        assertThat(Tcp.connectionRefused()).startsWith("ConnectException");
        assertThat(Tcp.portAlreadyInUse()).isEqualTo("BindException: Address already in use");
    }

    @Test
    void sequentialServerMakesTheSecondClientWaitButThreadPerClientDoesNot() throws Exception {
        assertThat(Tcp.oneClientAtATime()).isEqualTo("second client got: no answer within 300 ms (SocketTimeoutException)");
        assertThat(Tcp.threadPerClient()).isEqualTo("second client got: Server received: quick question");
    }

    @Test
    void addressesAndUrisSplitIntoParts() throws Exception {
        assertThat(Addresses.loopback()).isEqualTo("localhost is 127.0.0.1, loopback true, 192.168.1.10 is a site local address true, raw bytes 4");
        assertThat(Addresses.uriParts()).isEqualTo("scheme https, host shop.example.com, port 8443, path /orders/42, query status=open&page=2, fragment top");
        assertThat(Addresses.defaultPort()).isEqualTo("no port written gives getPort -1");
    }

    @Test
    void urlEncodingRoundTripsAndBadUrisAreRejected() {
        assertThat(Addresses.encoding()).isEqualTo("encoded Xin+ch%C3%A0o+%26+b%E1%BA%A1n%3Dok, decoded back equal true");
        assertThat(Addresses.badUri()).startsWith("IllegalArgumentException: Illegal character in path");
        assertThat(Addresses.unknownHost()).startsWith("UnknownHostException: host-that-does-not-exist.invalid");
    }

    @Test
    void httpGetAndPostReturnTheServerText() throws Exception {
        assertThat(Http.get()).isEqualTo("status 200, body 'Hello from the lab server', content type text/plain; charset=utf-8, protocol HTTP_1_1");
        assertThat(Http.post()).isEqualTo("POST received 8 characters: Xin chào");
    }

    @Test
    void anErrorStatusIsAResponseNotAnException() throws Exception {
        assertThat(Http.notFoundIsNotAnException()).isEqualTo("status 404 came back as a normal response, no exception was thrown");
    }

    @Test
    void timeoutsAndMissingServersAreExceptions() throws Exception {
        assertThat(Http.timeout()).isEqualTo("HttpTimeoutException: request timed out");
        assertThat(Http.connectionRefused()).isEqualTo("server gone: ConnectException");
        assertThat(Http.asyncCalls()).isEqualTo("two calls in flight together, first 'Hello from the lab server', second status 200");
    }
}
