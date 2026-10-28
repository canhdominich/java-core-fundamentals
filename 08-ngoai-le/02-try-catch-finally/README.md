# Bài 02 - try, catch, finally và try-with-resources: thứ tự chạy, cạm bẫy và dọn dẹp tài nguyên

Mã nguồn đi kèm bài học **try, catch, finally** (chương 8: Xử lý ngoại lệ) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ ghi lại vết của ba kết cục của cùng một câu lệnh `try` (không lỗi, bị bắt, không khớp), thấy `finally` chạy cả khi ngoại lệ đang đi lên, giá trị `return` được chốt trước khi `finally` chạy và `return` trong `finally` làm mất ngoại lệ, so sánh nuốt lỗi với bọc lỗi kèm nguyên nhân, dùng multi-catch, và kiểm chứng `try-with-resources` đóng tài nguyên theo thứ tự ngược, đóng cả khi thân lỗi, gắn lỗi khi đóng vào danh sách bị che (suppressed) thay vì thay thế lỗi gốc, trong khi `finally` viết tay thì nuốt hoặc thay thế.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
02-try-catch-finally/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/exceptions/handling/
    Trace.java                  ghi lại thứ tự các bước để in ra
    Flow.java                   try, catch, finally, return, nuốt lỗi, bọc lỗi, multi-catch
    Resource.java               tài nguyên AutoCloseable có thể lỗi khi dùng và khi đóng
    Resources.java              try-with-resources so với finally viết tay
    HandlingDemo.java           chạy mọi ví dụ và năm lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/exceptions/handling/
    HandlingTest.java           mười test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.exceptions.handling.HandlingDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
input abc        = [try starts, catch NumberFormatException, finally, after the try statement]
return value captured = 1
closing order      = [open A, open B, use A, use B, close B, close A]
suppressed         = caught IllegalStateException: use failed on A | suppressed IllegalArgumentException: close failed on A
```

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 10, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Deep`, `FinallyDemo`, `Practice`, `SafeReader`, `Selfcheck`, `Selfcheck2`, `Swallow`, `Tally`, `Trace`. Thư mục con: `fails/`.
