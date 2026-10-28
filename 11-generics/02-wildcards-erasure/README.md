# Bài 02 - Chặn kiểu, ký tự đại diện, PECS và xoá kiểu

Mã nguồn đi kèm bài học **Bounded types và wildcards** (chương 11: Generics) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ dùng chặn trên (`T extends Number`, hai chặn `Number & Comparable`), ký tự đại diện `? extends` để đọc và `? super` để ghi, viết `copy(dest, src)` theo nguyên tắc PECS, đọc thông báo lỗi khi cố thêm vào `List<? extends Number>`, thấy `ArrayList<String>` và `ArrayList<Integer>` là cùng một lớp lúc chạy, chặn trên thay thế `T` sau khi xoá kiểu, trình biên dịch tạo phương thức cầu (bridge) cho `compareTo`, dùng `Class<T>` để tạo mảng và đối tượng, và đọc các lỗi `name clash` và `unexpected type`.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
02-wildcards-erasure/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/generics/wildcards/
    Bounds.java                 chặn trên và hai chặn
    Wildcards.java              ? extends, ? super, ?, và copy theo PECS
    StringBox.java              lớp cài đặt Comparable<StringBox>, sinh phương thức cầu
    Holder.java                 lớp có chặn T extends Number
    Erasure.java                đo xoá kiểu, phương thức cầu, Class làm thẻ kiểu
    WildcardDemo.java           chạy mọi ví dụ và chín lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/generics/wildcards/
    WildcardTest.java           bảy test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.generics.wildcards.WildcardDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
erasure            = ArrayList<String> and ArrayList<Integer> share one class: true, the class name is ArrayList
erased bound       = Holder<T extends Number>: get() returns Number, set takes Number (the bound replaces T)
bridge method      = StringBox has 1 compiler-made bridge method: compareTo(Object)
same erasure                   = [line 1: name clash: f(java.util.List<java.lang.Integer>) and f(java.util.List<java.lang.String>) have the same erasure]
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

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `BoundsDemo`, `Check`, `PriceBoard`, `PriceSum`, `PriceTools`.
