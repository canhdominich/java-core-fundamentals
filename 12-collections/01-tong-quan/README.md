# Bài 01 - Tổng quan Collections Framework: họ interface, thứ tự duyệt, null, bất biến và chi phí tìm kiếm

Mã nguồn đi kèm bài học **Tổng quan Collections Framework** (chương 12: Collections Framework) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ in các interface mà bảy lớp thông dụng cài đặt để thấy họ `Collection` và `Map` riêng biệt, so sánh thứ tự duyệt của `List`, `HashSet`, `LinkedHashSet` và `TreeSet` trên cùng năm từ, kiểm tra trùng lặp và `null` ở từng loại, thấy `List.of` bất biến và từ chối `null`, đếm số lần `equals` khi tìm khoá cuối cùng trong danh sách (1000 và 100000 lần) so với `HashSet` (một lần), dùng các hàm của `Collections` và đọc năm thông báo lỗi biên dịch.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-tong-quan/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/collections/overview/
    CountedKey.java             khoá đếm số lần equals được gọi
    Overview.java               họ interface, thứ tự duyệt, null, bất biến, chi phí tìm, Collections
    OverviewDemo.java           chạy mọi ví dụ và năm lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/collections/overview/
    OverviewTest.java           bảy test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.collections.overview.OverviewDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
orders       = list [pear, apple, fig, banana, cherry], HashSet [banana, apple, cherry, pear, fig], LinkedHashSet [pear, apple, fig, banana, cherry], TreeSet [apple, banana, cherry, fig, pear]
lookup 100000= searching for the last of 100000 keys: list true after 100000 equals calls, HashSet true after 1 equals call
immutable    = List.of add: UnsupportedOperationException, List.of with null: NullPointerException, a mutable copy has 4 elements
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

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Check40`, `Clean`, `Kitchen`, `KitchenOverview`, `LookupCost`, `Orders`, `Views`.
