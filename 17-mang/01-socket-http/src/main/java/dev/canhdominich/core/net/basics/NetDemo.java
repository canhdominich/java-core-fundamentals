package dev.canhdominich.core.net.basics;

import java.util.List;

public final class NetDemo {

    private NetDemo() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("tcp echo            = " + Tcp.echo());
        System.out.println("lines               = " + Tcp.messagesAreLines());
        System.out.println("refused             = " + Tcp.connectionRefused());
        System.out.println("port in use         = " + Tcp.portAlreadyInUse());
        System.out.println("one at a time       = " + Tcp.oneClientAtATime());
        System.out.println("thread per client   = " + Tcp.threadPerClient());
        System.out.println("addresses           = " + Addresses.loopback());
        System.out.println("uri parts           = " + Addresses.uriParts());
        System.out.println("default port        = " + Addresses.defaultPort());
        System.out.println("url encoding        = " + Addresses.encoding());
        System.out.println("bad uri             = " + Addresses.badUri());
        System.out.println("unknown host        = " + Addresses.unknownHost());
        System.out.println("http get            = " + Http.get());
        System.out.println("http 404            = " + Http.notFoundIsNotAnException());
        System.out.println("http post           = " + Http.post());
        System.out.println("http timeout        = " + Http.timeout());
        System.out.println("http async          = " + Http.asyncCalls());
        System.out.println("http server gone    = " + Http.connectionRefused());
        List<String> wrongPort = Javac.messages("A", "import java.net.*;\nclass A { void f() throws Exception { new Socket(\"localhost\", \"80\"); } }");
        List<String> unreported = Javac.messages("B", "import java.net.*;\nclass B { void f() { new ServerSocket(5000); } }");
        List<String> responseType = Javac.messages("C", "import java.net.http.*;\nclass C { void f(HttpResponse<String> r) { int s = r.body(); } }");
        System.out.println("compile port type   = " + wrongPort);
        System.out.println("compile unreported  = " + unreported);
        System.out.println("compile body type   = " + responseType);
    }
}
