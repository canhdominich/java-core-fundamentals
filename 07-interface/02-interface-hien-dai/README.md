# Bài 02 - Interface hiện đại: default, static, private, xung đột và interface hàm

Mã nguồn đi kèm bài học **default, static và interface hiện đại** (chương 7: Interface và lớp trừu tượng) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy một `Logger` có phương thức `default` dùng chung một phương thức `private`, thấy lớp cài đặt chỉ cần viết `log` mà vẫn có `info` và `error`, ghi đè một phương thức `default` và gọi lại bản của interface bằng `Logger.super`, giải quyết xung đột hai phương thức `default` cùng tên (`Duck`), xem quy tắc "phương thức của lớp thắng" (`Goose`), và xây các quy tắc kiểm tra chuỗi từ một interface hàm `StringRule` với `and`, `negate` và phương thức tạo `static`.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
02-interface-hien-dai/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/iface/modern/
    Logger.java                 default, private và static trong một interface
    CollectingLogger.java       cài đặt chỉ cần log
    PrefixLogger.java           ghi đè default và gọi Logger.super
    Walker.java                 interface có default move
    Swimmer.java                interface khác có default move
    Duck.java                   giải quyết xung đột hai default
    Animal.java                 lớp có phương thức move
    Goose.java                  lớp kế thừa Animal và cài đặt Walker
    StringRule.java             interface hàm với default và static
    ModernDemo.java             chạy mọi ví dụ và sáu lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/iface/modern/
    ModernTest.java             bảy test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.iface.modern.ModernDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
inherited defaults    = [[INFO] started, [ERROR] failed]
conflict resolved     = Duck walks and swims
class wins            = Goose moves (class method)
two unrelated defaults   = [line 1: types A and B are incompatible;]
```

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
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |
