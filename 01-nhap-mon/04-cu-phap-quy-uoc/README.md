# Bài 04 - Cú pháp cơ bản và quy ước: tên hợp lệ, quy ước đặt tên, comment và dấu chấm phẩy

Mã nguồn đi kèm bài học **Cú pháp cơ bản và quy ước** (chương 1: Nhập môn Java) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy bốn thí nghiệm: hỏi chính trình biên dịch tên nào là hợp lệ hay là từ khóa (`IdentifierRules`), kiểm tra bằng một bộ lọc nhỏ các quy ước đặt tên và thấy vi phạm quy ước vẫn biên dịch được (`NamingDemo`), chứng minh comment không đổi bytecode nhưng có thể đổi số dòng (`CommentsDemo`), và tái hiện lỗi dấu chấm phẩy thừa sau `if` mà `javac` chỉ cảnh báo khi bật `-Xlint:empty` (`EmptyStatementTrap`).

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
04-cu-phap-quy-uoc/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/syntax/
    IdentifierRules.java        phân loại một từ: định danh hợp lệ, từ khóa hay không phải định danh
    NamingConventions.java      bộ kiểm tra quy ước đặt tên bằng biểu thức chính quy
    NamingDemo.java             chạy bộ kiểm tra trên các tên mẫu
    SourceChecks.java           biên dịch một chuỗi mã nguồn và gom thông báo, bytecode
    CommentsDemo.java           so dấu vân tay bytecode của mã có và không có comment
    EmptyStatementTrap.java     lỗi dấu chấm phẩy thừa sau if và cảnh báo của javac
  src/test/java/dev/canhdominich/core/syntax/
    SyntaxTest.java             chín test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.syntax.IdentifierRules
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.syntax.NamingDemo
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.syntax.CommentsDemo
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.syntax.EmptyStatementTrap
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15), `CommentsDemo` và `EmptyStatementTrap` cho:

```text
-g:none (no debug info)
  plain                   98d0fc0d0b94
  comment on the same line 98d0fc0d0b94
  comments on other lines  98d0fc0d0b94
default debug info (line numbers kept)
  plain                   057fe63c5f3f
  comment on the same line 057fe63c5f3f
  comments on other lines  94dbda1763b0
label(10) = adult   (a ten-year-old became an adult)
label(30) = adult
default compile : []
with -Xlint:empty: [WARNING line 4: empty statement after if]
```

Mã băm cụ thể có thể khác ở JDK khác; điều cố định là ba mã đầu giống nhau và mã cuối của khối thứ hai khác hai mã trước.

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 9, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| Mã băm trong `CommentsDemo` khác bài | Phiên bản JDK khác sinh bytecode hơi khác | So quan hệ giữa các mã băm trong cùng một lần chạy, không so giá trị tuyệt đối |
| `NamingDemo` in `compiles? ... -> true` cho tên sai quy ước | Trình biên dịch không kiểm tra quy ước | Dùng công cụ kiểm tra phong cách như Checkstyle trong dự án thật |
