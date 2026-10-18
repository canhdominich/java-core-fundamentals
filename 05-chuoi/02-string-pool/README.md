# Bài 02 - Tính bất biến của String và String Constant Pool

Mã nguồn đi kèm bài học **Tính bất biến của String và String Constant Pool** (chương 5: Chuỗi) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy các phép đo danh tính đối tượng để thấy khi nào `==` đúng với chuỗi (hai literal, `intern`), khi nào sai (`new String`, `substring`, nối lúc chạy), những phương thức trả về chính đối tượng cũ khi không có gì để đổi, đếm số đối tượng phân biệt khi tạo 100000 chuỗi bằng ba cách, và xem vì sao bất biến cho phép `String` làm khoá bản đồ an toàn trong khi một danh sách thay đổi được thì bị mất khỏi `HashMap`.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
02-string-pool/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/pool/
    PoolIdentity.java           danh tính đối tượng, intern, đếm đối tượng phân biệt, nối bằng +=
    WhyImmutable.java           khoá HashMap thay đổi được so với String, va chạm mã băm, mã băm theo nội dung
  src/test/java/dev/canhdominich/core/pool/
    PoolTest.java               mười test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.pool.PoolIdentity
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.pool.WhyImmutable
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
100000 new String    = 100000 distinct objects
100000 intern        = 1 distinct object
100000 literals      = 1 distinct object
1000 times += "x"    = 1000 distinct objects
collisions        = hash of Aa 2112, hash of BB 2112, equal strings false, lookups 1 and 2
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
