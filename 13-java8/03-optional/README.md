# Bài 03 - Optional: biểu diễn giá trị có thể vắng, chuỗi map và flatMap, orElse và orElseGet

Mã nguồn đi kèm bài học **Optional** (chương 13: Java 8 trở lên) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ tạo `Optional` bằng `of`, `ofNullable`, `empty` và thấy `of(null)` ném `NullPointerException`, `get` trên `Optional` rỗng ném `NoSuchElementException`, `orElse` luôn tạo giá trị mặc định còn `orElseGet` chỉ tạo khi cần, `orElseThrow` biến sự vắng mặt thành ngoại lệ tự chọn, chuỗi `flatMap`, `map`, `filter`, `or`, `ifPresentOrElse`, so sánh ba tầng kiểm tra `null` với một chuỗi `Optional`, dùng `Optional.stream`, và nhận ra mẫu xấu `isPresent` rồi `get`.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
03-optional/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/java8/optional/
    Address.java                bản ghi địa chỉ, thành phố có thể null
    User.java                   người dùng có địa chỉ và biệt danh có thể vắng
    Users.java                  tra cứu trả Optional, so sánh kiểm tra null với chuỗi Optional
    Basics.java                 tạo, get, orElse, orElseGet, orElseThrow, chuỗi, stream
    OptionalDemo.java           chạy mọi ví dụ và các lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/java8/optional/
    OptionalTest.java           tám test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.java8.optional.OptionalDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
get on empty        = get on an empty Optional: NoSuchElementException with message 'No value present'
orElse vs orElseGet = orElse on a present value built the default 1 time, orElseGet built it 0 more times, and built it when the value was absent: default (1 time)
nested lookups      = An HANOI, Binh (no city) unknown, Chi (no address) unknown, Zed (no user) unknown | with null checks: ...
```

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 8, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |
