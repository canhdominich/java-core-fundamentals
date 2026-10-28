# Bài 03 - Hello World: biên dịch, chạy và đọc lỗi đầu tiên

Mã nguồn đi kèm bài học **Cài đặt Java và chương trình Hello World** (chương 1: Nhập môn Java) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy chương trình `Hello` bằng hai cách (biên dịch trước rồi `java Hello`, và chạy thẳng file nguồn `java Hello.java`) và đo chênh lệch thời gian, rồi cố ý tạo chín lỗi thường gặp ở ngày đầu học Java (thiếu dấu chấm phẩy, sai tên lớp, sai tên tệp, không có `main`, gõ `Hello.class`, thiếu classpath, ngoại lệ lúc chạy, mã thoát) và đọc từng thông báo của `javac` và `java`.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
03-hello-world/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/hello/
    Hello.java                  chương trình Hello World nhận tên từ tham số dòng lệnh
    Compile.java                biên dịch một chuỗi mã nguồn và gom thông báo lỗi
    Run.java                    chạy lệnh java trong một thư mục và gom kết quả
    BrokenPrograms.java         các chương trình cố ý sai
    HelloErrors.java            chạy chín tình huống lỗi và in thông báo
    LaunchModes.java            so sánh biên dịch trước với chạy thẳng file nguồn
  src/test/java/dev/canhdominich/core/hello/
    HelloTest.java              bảy test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.hello.HelloErrors
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.hello.LaunchModes
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.hello.Hello -Dmain.arg=Lan
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15, Linux):

```text
[compile] missing semicolon -> line 3: ';' expected
[compile] unknown symbol -> line 3: cannot find symbol
[compile] public class in the wrong file -> line 1: class Wrong is public, should be declared in a file named Wrong.java
[run]     no main method -> exit 1, Error: Main method not found in class Hello, please define the main method as:
[run]     wrong class name -> exit 1, Error: Could not find or load main class Helo
[run]     class name with .class -> exit 1, Error: Could not find or load main class Hello.class
[run]     exception in main -> exit 1, Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 2
[run]     System.exit(3) -> exit 3, about to exit
[run]     no classpath -> exit 1, Error: Could not find or load main class Hello
```

`LaunchModes` in thời gian trung vị của năm lần chạy mỗi cách; cách chạy thẳng file nguồn chậm hơn nhiều lần vì mỗi lần phải biên dịch lại.

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 7, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `Error: Could not find or load main class Hello` | Sai tên lớp, sai thư mục hiện tại hoặc thiếu `-cp` | Đứng đúng thư mục chứa `Hello.class` và chạy `java -cp . Hello` |
| Thông báo lỗi `javac` bằng tiếng khác | Hệ điều hành đặt ngôn ngữ khác | Chạy `javac -J-Duser.language=en Hello.java` |
| `LaunchModes` không tìm thấy `Hello.java` | Chạy không phải ở thư mục dự án | Chạy `mvn` ngay trong thư mục `03-hello-world` |

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Boom`, `Door`, `Hello`, `Welcome`.
