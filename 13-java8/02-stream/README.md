# Bài 02 - Stream API: đường ống lười, collect, groupingBy, toMap, luồng nguyên thủy

Mã nguồn đi kèm bài học **Stream API** (chương 13: Java 8 trở lên) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ dựng đường ống `filter`, `map`, `distinct`, `sorted`, `collect` trên sáu đơn hàng, đo tính lười (xây đường ống chạm 0 phần tử, `findFirst` dừng sau hai phần tử), thấy luồng chỉ dùng được một lần, nhóm theo khách bằng `groupingBy` với `counting` và `summingLong`, tách đôi bằng `partitioningBy`, thấy `toMap` ném lỗi khi trùng khoá nếu không có hàm gộp, dùng `IntStream` cho tổng, thống kê, trung bình, dùng `flatMap` và `joining`, và so sánh `Collectors.toList` với `Stream.toList`.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
02-stream/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/java8/stream/
    Order.java                  bản ghi đơn hàng với tổng tiền
    Pipelines.java              các đường ống, collector, luồng nguyên thủy
    StreamDemo.java             chạy mọi ví dụ và ba lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/java8/stream/
    StreamTest.java             chín test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.java8.stream.StreamDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
basic pipeline    = customers with an order of at least 100000: [Binh, Chi], revenue 800000
laziness          = building the pipeline touched 0 elements, findFirst returned 2 after seeing 2 elements
grouping          = orders per customer {An=2, Binh=2, Chi=2}, revenue per customer {An=130000, Binh=410000, Chi=260000}, ...
```

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
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Check45`, `Lazy`, `OrderBoard`, `Report`, `StreamBoard`.
