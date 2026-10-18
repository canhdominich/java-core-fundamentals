# Bài 02 - Tham số, đối số, pass-by-value và varargs

Mã nguồn đi kèm bài học **Tham số, đối số, Pass-by-Value và Varargs** (chương 3: Phương thức) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy một loạt phép thử để thấy Java luôn truyền bản sao của giá trị: số nguyên không đổi ở nơi gọi, trường của đối tượng đổi được, gán lại tham số thì không, hoán đổi hai tham số không thành công, chuỗi và `StringBuilder` khác nhau thế nào, và vì sao sắp xếp mảng tại chỗ khác với sắp xếp một bản sao. Sau đó là `varargs` với 0, 1, nhiều giá trị hoặc một mảng, và sáu lỗi biên dịch về tham số.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
02-tham-so-pass-by-value/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/parameters/
    Box.java                    lớp nhỏ có một trường value để quan sát
    PassByValue.java            truyền giá trị, gán lại tham số, hoán đổi, chuỗi, mảng
    VarargsDemo.java            varargs, mảng dùng chung, chọn nạp chồng
    ParameterErrors.java        sáu lỗi biên dịch về tham số
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/parameters/
    ParametersTest.java         mười hai test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.parameters.PassByValue
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.parameters.VarargsDemo
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.parameters.ParameterErrors
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
changeInt(5)               -> n = 5
changeField(box)           -> Box(99)
reassign(box)              -> Box(5)
swap(a, b)                 -> a = Box(1), b = Box(2)
sum(new int[] {4, 5})      = 9
varargs parameter must be the last parameter
```

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
