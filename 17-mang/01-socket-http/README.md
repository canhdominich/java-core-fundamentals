# Bài 01 - Lập trình mạng: socket TCP, địa chỉ, URI, HttpClient và máy chủ phục vụ nhiều khách

Mã nguồn đi kèm bài học **Networking cơ bản** (chương 17: Lập trình mạng) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ dựng máy chủ echo bằng `ServerSocket` và khách bằng `Socket` trên cổng ngẫu nhiên của máy cục bộ, thấy `readLine` chỉ trả về khi có dấu xuống dòng (hết hạn `SocketTimeoutException`), gặp `ConnectException` và `BindException`, so máy chủ phục vụ từng khách một với máy chủ một luồng cho mỗi khách (khách thứ hai chờ 300 ms không có trả lời hay được trả lời ngay), tách `URI` thành thành phần, mã hóa URL bằng `URLEncoder`, và dùng `HttpClient` gọi máy chủ HTTP cục bộ: GET, POST, mã 404 là phản hồi chứ không phải ngoại lệ, hết hạn `HttpTimeoutException`, gọi bất đồng bộ và máy chủ đã tắt. Mọi thí nghiệm chạy trên `127.0.0.1`, không cần Internet.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-socket-http/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/net/basics/
    EchoServer.java             máy chủ echo, chạy tuần tự hoặc một luồng cho mỗi khách
    Tcp.java                    khách TCP, dòng, lỗi kết nối, cổng bận, hai kiểu máy chủ
    Addresses.java              InetAddress, URI, mã hóa URL, lỗi tên miền
    Http.java                   HttpServer cục bộ và HttpClient: GET, POST, hết hạn, async
    NetDemo.java                chạy mọi ví dụ và các lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/net/basics/
    NetTest.java                chín test, tất cả chạy trên máy cục bộ
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.net.basics.NetDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15, Linux), hai lần chạy liên tiếp cho cùng kết quả:

```text
tcp echo            = Server received: Hello server
one at a time       = second client got: no answer within 300 ms (SocketTimeoutException)
thread per client   = second client got: Server received: quick question
http 404            = status 404 came back as a normal response, no exception was thrown
http timeout        = HttpTimeoutException: request timed out
```

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 9, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |
