# Bài 01 - Generics: lớp và phương thức tổng quát, kiểm tra kiểu lúc biên dịch, kiểu thô và đóng gói

Mã nguồn đi kèm bài học **Generics là gì?** (chương 11: Generics) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ so sánh một hộp giữ `Object` (lỗi ép kiểu chỉ lộ lúc chạy) với `Box<T>` (không cần ép kiểu, sai kiểu là lỗi biên dịch), viết phương thức tổng quát `first`, `max` có chặn trên, `listOf`, `Pair<A, B>`, dùng `map` để đổi kiểu của hộp, thấy một kiểu thô (raw type) đưa `Integer` vào `List<String>` và lỗi chỉ lộ ở lần đọc, quan sát bộ nhớ đệm `Integer` (127 bằng nhau còn 128 thì không) và việc mở hộp `null` ném `NullPointerException`, và đọc tám thông báo lỗi biên dịch của generics.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-generics/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/generics/basics/
    ObjectBox.java              hộp giữ Object, trước khi có generics
    Box.java                    hộp tổng quát Box<T> với phương thức map
    Pair.java                   bản ghi tổng quát Pair<A, B>
    Util.java                   phương thức tổng quát first, max, listOf, pair
    GenericsDemo.java           chạy mọi ví dụ và tám lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/generics/basics/
    GenericsTest.java           bảy test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.generics.basics.GenericsDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
before generics   = the mistake shows up only at run time: ClassCastException
generic methods   = first pear and 7, max pear and 42, listOf [1, 2, 3], pair Pair[first=a, second=1], swapped Pair[first=1, second=a]
boxing identity   = 127 == 127 is true, 128 == 128 is false, equals gives true
new T[3]               = [line 1: generic array creation]
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
