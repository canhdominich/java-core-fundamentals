# Bài 02 - JDK, JRE và JVM: thư mục java.home, nạp lớp, JIT và ma trận phiên bản

Mã nguồn đi kèm bài học **Cách Java hoạt động: JDK, JRE, JVM** (chương 1: Nhập môn Java) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ liệt kê công cụ có trong `java.home` của máy mình, phân biệt lệnh `javac` với module trình biên dịch có trong runtime, xem JVM đang chạy với bộ gom rác và bộ nhớ nào, đếm số lớp được nạp lúc dùng lần đầu, đo chương trình chạy nhanh hơn bao nhiêu nhờ trình biên dịch JIT so với chế độ chỉ thông dịch (`-Xint`), và chạy cùng một lớp biên dịch cho Java 8, 11, 17 trên nhiều JVM khác nhau để thấy ma trận tương thích.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
02-jdk-jre-jvm/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/jvm/
    JdkLayout.java              liệt kê công cụ trong java.home và các module
    CompilerCheck.java          so lệnh javac với API trình biên dịch
    JvmFacts.java               tên JVM, bộ gom rác, heap tối đa, tham số JVM
    ClassLoadingFacts.java      đếm số lớp được nạp khi dùng lần đầu
    HotLoop.java                vòng lặp nóng chạy trong tiến trình con
    JitWarmup.java              chạy HotLoop ở chế độ mặc định và -Xint rồi so
    VersionMatrix.java          chạy lớp biên dịch cho release 8, 11, 17 trên nhiều JVM
  src/test/java/dev/canhdominich/core/jvm/
    JvmTest.java                năm test
```

## Chạy thí nghiệm

Chạy từng chương trình (mỗi lệnh chạy một lớp):

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.jvm.JdkLayout
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.jvm.CompilerCheck
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.jvm.JvmFacts
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.jvm.ClassLoadingFacts
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.jvm.JitWarmup
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.jvm.VersionMatrix
```

`JitWarmup` mất khoảng một phút vì chế độ `-Xint` rất chậm; có thể thu nhỏ bằng `-Dmain.arg=100000000`. Để thử trên nhiều JVM, truyền đường dẫn các lệnh `java` cách nhau bằng dấu phẩy: `-Dmain.arg=/duong/dan/java8/bin/java,/duong/dan/java11/bin/java`. Để so hai bản cài, chạy cùng lớp bằng từng JVM: `/duong/dan/bin/java -cp target/classes dev.canhdominich.core.jvm.JdkLayout`.

## Kết quả mong đợi

Trên máy đã kiểm chứng (Ubuntu, OpenJDK 17.0.15 chỉ có runtime, kèm JRE 8 và 11 và hai JDK 21, 25 từ công cụ khác), `VersionMatrix` cho:

```text
openjdk version "1.8.0_452"
  class from --release 8  : hello from Java 1.8
  class from --release 11 : UnsupportedClassVersionError: file is 55.0, this JVM supports up to 52.0
  class from --release 17 : UnsupportedClassVersionError: file is 61.0, this JVM supports up to 52.0
openjdk version "11.0.27" 2025-04-15
  class from --release 17 : UnsupportedClassVersionError: file is 61.0, this JVM supports up to 55.0
```

`JitWarmup` cho tổng thời gian khoảng 3000 ms (mặc định) so với khoảng 50000 ms (`-Xint`) trên máy bốn lõi này; con số của bạn khác nhưng tỉ lệ vẫn là hàng chục lần.

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 5, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `javac` không chạy được nhưng `mvn` vẫn biên dịch | Runtime chỉ có module trình biên dịch, không có lệnh `javac` | Cài gói JDK (ví dụ `openjdk-17-jdk`), kiểm tra `javac -version` |
| `JitWarmup` quá chậm | Chế độ `-Xint` chạy 300 triệu bước bằng thông dịch | Đặt `-Dmain.arg=100000000` |
| `VersionMatrix` báo không tìm thấy lệnh java | Đường dẫn truyền vào sai | Chạy `which -a java` hoặc `update-alternatives --list java` để lấy đường dẫn đúng |

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `JvmReport`, `MachineCheck`, `ToolBox`, `Warmup`.
