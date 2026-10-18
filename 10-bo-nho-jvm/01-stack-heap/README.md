# Bài 01 - Bộ nhớ: stack, heap, kích thước đối tượng và OutOfMemoryError

Mã nguồn đi kèm bài học **Quản lý bộ nhớ: Stack và Heap** (chương 10: Bộ nhớ và JVM) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ đo kích thước thật của đối tượng (`Object` 16 byte, hai `int` 24 byte, ba `long` 40 byte, mảng `int[10]` 56 byte, `String` 24 byte) bằng số byte mà luồng đã cấp phát, thấy biến cục bộ riêng cho từng luồng trong khi mảng chung trên heap làm mất cập nhật, chạy một chương trình con với heap 64 MB để gặp `OutOfMemoryError: Java heap space`, và quan sát một tham số kiểu nguyên thủy là bản sao trên stack còn phần tử mảng nằm trên heap.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-stack-heap/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/memory/
    Child.java                  chạy một lớp trong tiến trình Java mới, đo byte cấp phát theo đối tượng
    SizeProbe.java              đo kích thước tám loại đối tượng
    OutOfMemoryProbe.java       cấp phát đến khi hết heap giới hạn
    ThreadStacks.java           biến cục bộ của luồng so với mảng dùng chung
    MemoryDemo.java             chạy mọi ví dụ
  src/test/java/dev/canhdominich/core/memory/
    MemoryTest.java             năm test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.memory.MemoryDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15, con trỏ nén mặc định), các kết quả cố định:

```text
new Object()           = 16 bytes
new Pair (two ints)    = 24 bytes
new Wide (three longs) = 40 bytes
new int[10]            = 56 bytes
OutOfMemoryError message: Java heap space
```

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
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |
