# Bài 01 - Java là gì: bytecode, JVM và sàn phiên bản

Mã nguồn đi kèm bài học **Java là gì?** (chương 1: Nhập môn Java) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ tự biên dịch cùng một đoạn mã nguồn cho ba phiên bản Java (8, 11, 17) ngay trong bộ nhớ, đọc phần đầu của file `.class` để thấy số nhận dạng `CAFEBABE` và số phiên bản, nạp thử một file `.class` quá mới vào JVM cũ hơn để thấy lỗi `UnsupportedClassVersionError`, rồi dùng `javap` xem cùng một dòng nối chuỗi được dịch khác nhau ở hai phiên bản. Bài cũng in các thuộc tính hệ điều hành để thấy phần nào của Java thay đổi theo nền tảng.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-java-la-gi/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/intro/
    InMemoryCompiler.java       biên dịch mã nguồn trong bộ nhớ bằng javax.tools
    ClassFileHeader.java        đọc magic, minor, major của file .class
    Sources.java                đoạn mã nguồn Greeter dùng cho mọi thí nghiệm
    ClassFileInspector.java     biên dịch cho release 8, 11, 17 và in phần đầu file
    TooNewBytecode.java         nạp file .class có phiên bản quá mới
    BytecodePeek.java           dùng javap xem bytecode của cùng một dòng nối chuỗi
    PlatformFacts.java          in các thuộc tính phụ thuộc hệ điều hành
  src/test/java/dev/canhdominich/core/intro/
    ClassFileTest.java          sáu test
```

## Chạy thí nghiệm

Chạy từng chương trình (mỗi lệnh chạy một lớp):

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.intro.ClassFileInspector
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.intro.TooNewBytecode
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.intro.BytecodePeek
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.intro.PlatformFacts
```

## Kết quả mong đợi

Kết quả trên máy đã kiểm chứng (JDK 17.0.15, Linux amd64); số byte và tên hệ điều hành của bạn có thể khác:

```text
running on Java 17
--release 8  : size=453 bytes magic=CAFEBABE minor=0 major=52 (Java 8)
--release 11 : size=695 bytes magic=CAFEBABE minor=0 major=55 (Java 11)
--release 17 : size=695 bytes magic=CAFEBABE minor=0 major=61 (Java 17)
```

`TooNewBytecode` in dòng `UnsupportedClassVersionError` cho major 62 và `loaded Greeter` cho major 52 và 61.

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 6, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `javac` hoặc `ToolProvider` báo không có trình biên dịch | Máy chỉ cài JRE, không có JDK | Cài JDK 17 trở lên và kiểm tra `javac -version` |
| `release version 17 not supported` | JDK trên máy cũ hơn 17 | Cài JDK 17 trở lên |
| Chữ tiếng Việt hiện sai trong terminal | Terminal không dùng UTF-8 | Đặt terminal sang UTF-8; pom đã ép `-Dfile.encoding=UTF-8` cho JVM |
