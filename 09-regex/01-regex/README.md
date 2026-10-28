# Bài 01 - Biểu thức chính quy: Pattern, Matcher, nhóm, tham lam và lười, kiểm tra dữ liệu

Mã nguồn đi kèm bài học **Regex trong Java** (chương 9: Biểu thức chính quy (Regex)) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ hỏi cùng một văn bản bằng `matches`, `lookingAt` và `find`, thử các lớp ký tự, neo và ranh giới từ, lượng từ, so sánh lượng từ tham lam với lười trên chuỗi HTML, đọc nhóm có tên và nhóm đánh số, thay thế có dùng lại nhóm, tách chuỗi, viết bộ kiểm tra điện thoại, email và mật khẩu, đọc các thông báo lỗi của `PatternSyntaxException`, đo vì sao nên biên dịch `Pattern` một lần (nhanh hơn `String.matches` trong vòng lặp), và xem một mẫu nhiều `.*` làm thời gian khớp tăng vọt.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-regex/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/regex/
    Basics.java                 matches, lookingAt, find, lớp ký tự, neo, lượng từ, cờ
    Groups.java                 nhóm có tên, nhóm đánh số, thay thế, tách chuỗi
    Validation.java             mẫu kiểm tra điện thoại, email, mật khẩu
    Backtracking.java           đo thời gian của mẫu nhiều .* và mẫu lồng nhau
    CompileReuse.java           so sánh String.matches với Pattern biên dịch sẵn
    RegexDemo.java              chạy mọi ví dụ, lỗi cú pháp regex và lỗi chuỗi Java
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/regex/
    RegexTest.java              mười hai test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.regex.RegexDemo
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.regex.Backtracking
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.regex.CompileReuse
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15), các kết quả cố định:

```text
three questions    = matches false, lookingAt false, find true at 6 giving 42
greedy and lazy    = greedy [<b>bold</b> and <i>italic</i>], lazy [<b>, </b>, <i>, </i>]
named groups       = year 2026, month 10, day 18, whole 2026-10-18, group count 3
unclosed group     = Unclosed group near index 4
```

Các dòng thời gian của `Backtracking` và `CompileReuse` phụ thuộc máy; chiều của chênh lệch thì không đổi.

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 12, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Bench`, `Breakages`, `CakeMessages`, `Greedy`, `OrderParser`, `OrderScan`, `Slow`, `Trace`.
