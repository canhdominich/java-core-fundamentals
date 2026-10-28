# Bài 03 - Interface và lớp trừu tượng: chọn công cụ đúng và kết hợp cả hai

Mã nguồn đi kèm bài học **So sánh interface và abstract class** (chương 7: Interface và lớp trừu tượng) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy một kho lưu trữ có interface `Storage` ở trên cùng, lớp trừu tượng `AbstractStorage` giữ trạng thái và quy tắc dùng chung, lớp cụ thể `MapStorage`, rồi hai lớp bọc (decorator) `AuditedStorage` và `ReadOnlyStorage` chỉ phụ thuộc vào interface. Bạn cũng đo sáu khác biệt mà trình biên dịch áp lên hai công cụ: trạng thái, nhiều cha, thêm phương thức mới, hàm khởi tạo và mức truy cập.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
03-interface-vs-abstract/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/iface/compare/
    Storage.java                interface với phương thức default isEmpty
    Auditable.java              interface khả năng ghi nhật ký
    AbstractStorage.java        lớp trừu tượng có trạng thái và phương thức khuôn final
    MapStorage.java             lớp cụ thể dùng HashMap
    AuditedStorage.java         lớp bọc ghi nhật ký, dùng cả hai interface
    ReadOnlyStorage.java        lớp bọc từ chối ghi
    CompareDemo.java            chạy mọi ví dụ và sáu lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/iface/compare/
    CompareTest.java            sáu test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.iface.compare.CompareDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
skeletal class      = size 2, writes 2, isEmpty false, blank key refused: key must not be blank, writes still 2
decorators          = value v, audit [put k, get k], read-only wrapper: storage is read-only, real storage size 1
add an abstract method    = [line 1: Old is not abstract and does not override abstract method remove(java.lang.String) in Storage2]
add a default method      = []
```

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 6, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Audited`, `BaseDemo`, `Combo`, `Practice`, `Trace`. Thư mục con: `fails/`.
