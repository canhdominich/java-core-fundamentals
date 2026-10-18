# Bài 01 - Date và Time API: LocalDate, Period, Duration, định dạng, múi giờ và lớp ngày giờ cũ

Mã nguồn đi kèm bài học **Date và Time API hiện đại** (chương 14: Date & Time API) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ tạo `LocalDate`, `LocalTime`, `LocalDateTime` và thấy chúng bất biến, đo vì sao cộng tháng ở cuối tháng cho kết quả lạ, phân biệt `Period` với `Duration`, định dạng và phân tích chuỗi (kể cả bẫy `YYYY` và trình phân tích mặc định chấp nhận 31/02), làm việc với `Instant` và `ZonedDateTime`, quan sát giờ mùa hè ở New York (giờ không tồn tại, giờ xảy ra hai lần, một ngày khác 24 giờ), tiêm `Clock` để test, và so với `Date` và `Calendar` cũ.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-java-time/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/datetime/javatime/
    Calendars.java              tạo, bất biến, cuối tháng, TemporalAdjusters
    Spans.java                  Period, Duration, ChronoUnit, tính tuổi
    Formats.java                định dạng, phân tích, bẫy YYYY, chế độ nghiêm ngặt
    Zones.java                  Instant, múi giờ, giờ mùa hè, Clock
    Legacy.java                 Date và Calendar cũ, cầu nối sang Instant
    JavaTimeDemo.java           chạy mọi ví dụ và các lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/datetime/javatime/
    JavaTimeTest.java           mười test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.datetime.javatime.JavaTimeDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
month end           = Jan 31 plus 1 month = 2026-02-28, leap year 2024 Jan 31 plus 1 month = 2024-02-29, Jan 31 plus 1 month plus 1 month = 2026-03-28, Jan 31 plus 2 months = 2026-03-31
period vs duration  = Period P11M30D (11 months 30 days), total days 364, Duration PT2H30M = 150 minutes
spring forward      = asked for 2026-03-08T02:30, got 2026-03-08T03:30 offset -04:00
day vs 24 hours     = plus 1 day 2026-03-08T12:00, plus 24 hours 2026-03-08T13:00
```

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 10, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |
