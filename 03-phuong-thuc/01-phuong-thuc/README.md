# Bài 01 - Phương thức: khai báo, kiểu trả về, return, ngăn xếp lời gọi và lỗi biên dịch

Mã nguồn đi kèm bài học **Phương thức** (chương 3: Phương thức) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy hai bản sao của cùng một quy tắc thuế và thấy chúng lệch nhau, rồi gom chúng vào một phương thức; xem các hình dạng của phương thức (trả số thực, trả `record`, trả `boolean`), so sánh `if` lồng nhau với câu lệnh bảo vệ, quan sát thứ tự tính đối số, in ngăn xếp lời gọi bằng `StackWalker`, và đọc sáu thông báo lỗi biên dịch liên quan tới phương thức.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-phuong-thuc/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/methods/
    Duplication.java            hai bản sao quy tắc thuế lệch nhau, và một phương thức dùng chung
    MethodShapes.java           trả số thực, record, boolean, câu lệnh bảo vệ, thứ tự tính đối số
    CallStack.java              in ngăn xếp lời gọi bằng StackWalker
    MethodErrors.java           sáu lỗi biên dịch về phương thức
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/methods/
    MethodsTest.java            tám test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.methods.Duplication
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.methods.MethodShapes
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.methods.CallStack
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.methods.MethodErrors
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
net 5: cart copy 5, invoice copy 6 | one method: cart 6, invoice 6
frames inside inner(), top first = [inner, middle, outer, main]
missing return       = [line 1: missing return statement]
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
