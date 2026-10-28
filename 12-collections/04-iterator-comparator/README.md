# Bài 04 - Iterator, Comparable và Comparator: tự viết Iterable, thứ tự tự nhiên, bộ so sánh và các cạm bẫy

Mã nguồn đi kèm bài học **Iterator, Comparable và Comparator** (chương 12: Collections Framework) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ tự viết một `Iterable` (lớp `Range`) dùng được trong `for-each` và thấy `NoSuchElementException` khi cạn, đặt thứ tự tự nhiên bằng `Comparable`, dựng thứ tự khác bằng `Comparator` (`comparing`, `thenComparing`, `reversed`, `nullsFirst`), thấy bẫy trừ hai số khi so sánh (tràn số), thấy `TreeSet` coi `compareTo` bằng 0 là trùng dù `equals` khác (như `BigDecimal` `1.0` và `1.00`), thấy `TreeSet` với `CASE_INSENSITIVE_ORDER` bỏ `B` vì trùng `b`, và đọc bốn thông báo lỗi biên dịch về `Comparable`.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
04-iterator-comparator/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/collections/ordering/
    Range.java                  Iterable tự viết với Iterator ẩn danh
    Student.java                lớp có thứ tự tự nhiên theo điểm
    Ordering.java               sắp xếp, Comparator, bẫy tràn số, TreeSet, null
    OrderingDemo.java           chạy mọi ví dụ và bốn lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/collections/ordering/
    OrderingTest.java           bảy test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.collections.ordering.OrderingDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
natural order     = sorted by compareTo (score) [Dung:70, Chi:80, Binh:80, An:90], stable for equal scores Chi before Binh: true
subtraction bug   = a - b gives [0, 2147483647, -2147483648, -5], Integer.compare gives [-2147483648, -5, 0, 2147483647]
equals consistency= 1.0 equals 1.00 is false, compareTo is 0, HashSet size 2, TreeSet size 1
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

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Board`, `Facts43`, `IterDemo`, `NaturalOrder`, `OrderSort`, `TrapDemo`.
