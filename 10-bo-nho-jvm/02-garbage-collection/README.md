# Bài 02 - Garbage Collection: khả năng truy cập, tham chiếu mạnh yếu, rò rỉ và bộ gom rác

Mã nguồn đi kèm bài học **Garbage Collection** (chương 10: Bộ nhớ và JVM) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ quan sát bộ gom rác bằng `WeakReference` và `SoftReference`: đối tượng chỉ bị thu hồi khi không còn tham chiếu mạnh nào, tham chiếu yếu bị xoá ngay sau lần gom đầu tiên còn tham chiếu mềm thì còn, hai đối tượng trỏ vào nhau (chu trình) vẫn bị thu hồi, một danh sách `static` giữ đối tượng sống tới khi bị xoá (rò rỉ kiểu Java), bộ nhớ đang dùng giảm khoảng 50 MB sau khi bỏ mảng, và ba bộ gom rác khác nhau (`Serial`, `Parallel`, `G1`) cùng chạy một chương trình tạo 400 MB rác với heap 64 MB.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
02-garbage-collection/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/gc/
    Child.java                  chạy một lớp trong tiến trình Java mới với tuỳ chọn riêng
    Reachability.java           tham chiếu mạnh, yếu, mềm, chu trình, rò rỉ bằng danh sách static
    Garbage.java                tạo 400 MB mảng ngắn hạn
    GcDemo.java                 chạy mọi ví dụ và so sánh ba bộ gom rác
  src/test/java/dev/canhdominich/core/gc/
    GcTest.java                 sáu test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.gc.GcDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
strong and weak       = while a strong reference exists the object survives true, after the last strong reference is gone it survives false
soft versus weak      = soft reference survives true, weak reference survives false
cycles                = two objects pointing at each other: first survives false, second survives false
collector             = -XX:+UseG1GC reports [Using G1], at least one young collection happened true
```

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
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |
