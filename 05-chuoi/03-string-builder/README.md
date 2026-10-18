# Bài 03 - StringBuilder và StringBuffer: sửa chuỗi tại chỗ, dung lượng và an toàn đa luồng

Mã nguồn đi kèm bài học **StringBuilder và StringBuffer** (chương 5: Chuỗi) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy các phép sửa chuỗi bằng `StringBuilder` (`append`, `insert`, `replace`, `delete`, `reverse`, `setLength`), thấy `equals` của builder là so sánh địa chỉ, quan sát dung lượng bắt đầu từ 16 và gần như gấp đôi mỗi lần đầy, đo `+=` chậm hơn `StringBuilder` hàng trăm lần với 50000 lần nối, và chứng kiến hai luồng làm mất dữ liệu của `StringBuilder` trong khi `StringBuffer` giữ đủ 400000 ký tự.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
03-string-builder/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/builder/
    BuilderOps.java             append, insert, replace, delete, reverse, setLength, equals, lỗi chỉ số
    Capacity.java               dung lượng ban đầu, cách tăng, trimToSize, ensureCapacity
    AppendSpeed.java            đo += so với StringBuilder với 50000 lần nối
    ThreadSafety.java           hai luồng cùng nối vào StringBuilder và StringBuffer
  src/test/java/dev/canhdominich/core/builder/
    BuilderTest.java            mười test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.builder.BuilderOps
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.builder.Capacity
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.builder.AppendSpeed
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.builder.ThreadSafety
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15), các kết quả cố định:

```text
editing             = >> JELLO!
equality traps      = equals false, same text true, compareTo 0, contentEquals true
initial capacity       = default 16, from text of 5 21, requested 100 100
capacity as 1000 chars are appended one by one = [16, 34, 70, 142, 286, 574, 1150]
```

Các dòng thời gian của `AppendSpeed` và kết quả của `ThreadSafety` thay đổi theo máy và theo lần chạy: `+=` luôn chậm hơn nhiều lần, `StringBuilder` dùng chung giữa hai luồng gần như luôn thiếu ký tự (đôi khi có luồng gặp lỗi), còn `StringBuffer` luôn đủ 400000.

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
