# Bài 02 - Đồng bộ hóa: race condition, synchronized, volatile, wait và notify, deadlock

Mã nguồn đi kèm bài học **Đồng bộ hóa** (chương 15: Đa luồng) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ đo race condition thật trên bộ đếm (`int` thường, `volatile int`, `synchronized` trên đối tượng mới mỗi lần, so với `synchronized` đúng), thấy khóa `synchronized` là reentrant, so cờ `volatile` với cờ thường, cài bộ đệm có giới hạn bằng `wait` và `notifyAll`, thấy `wait` ngoài `synchronized` ném `IllegalMonitorStateException`, tạo deadlock bằng hai thứ tự khóa ngược nhau và để JVM tự báo, rồi sửa bằng một thứ tự khóa hoặc `tryLock` có thời hạn.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
02-dong-bo/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/threads/sync/
    Races.java                  năm kiểu bộ đếm, khóa reentrant, bộ chạy nhiều luồng
    Visibility.java             cờ volatile so với cờ thường
    BoundedBuffer.java          bộ đệm giới hạn bằng wait và notifyAll
    Deadlocks.java              deadlock, thứ tự khóa chung, tryLock có thời hạn
    SyncDemo.java               chạy mọi ví dụ và các lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/threads/sync/
    SyncTest.java               chín test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.threads.sync.SyncDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15), ba lần chạy liên tiếp cho cùng kết quả:

```text
race                = expected 800000, synchronized method 800000, synchronized block 800000, plain int lost updates in 5 of 5 runs, volatile int in 5 of 5, synchronized on a new object each time in 5 of 5
plain flag          = reader saw the change false
wait without lock   = IllegalMonitorStateException: current thread is not owner
deadlock            = threads reported deadlocked 2, states BLOCKED and BLOCKED
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

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Compare`, `Deadlock`, `Handoff`, `LostUpdate`, `OrderFix`, `Reentrant`, `SalesCounter`, `Selfcheck`, `ShiftBoard`, `ShopTotals`, `Transfer`, `Visibility`, `WrongFix`.
