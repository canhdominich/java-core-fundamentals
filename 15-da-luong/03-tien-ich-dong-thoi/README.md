# Bài 03 - Tiện ích đồng thời: thread pool, Future, atomic, collection an toàn luồng, latch, semaphore, CompletableFuture

Mã nguồn đi kèm bài học **Concurrency Utilities** (chương 15: Đa luồng) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy chín tác vụ trên một pool ba luồng và đo số luồng chạy cùng lúc, thấy `submit` sau `shutdown` bị từ chối và `shutdownNow` trả lại các tác vụ đang chờ, lấy kết quả bằng `Callable` và `Future` (kể cả `ExecutionException` bọc lỗi và `get` có thời hạn rồi `cancel`), so `AtomicInteger` và `LongAdder` với bộ đếm thường, dùng `ConcurrentHashMap.merge`, `CopyOnWriteArrayList`, `BlockingQueue`, `CountDownLatch`, `Semaphore`, `CyclicBarrier`, và xâu chuỗi `CompletableFuture` với xử lý lỗi và pool tùy chọn.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
03-tien-ich-dong-thoi/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/threads/util/
    Pools.java                  thread pool cố định, shutdown, shutdownNow, tên luồng
    Futures.java                Callable, Future, hết hạn, hủy, CompletableFuture
    Safe.java                   atomic, ConcurrentHashMap, CopyOnWriteArrayList, BlockingQueue
    Coordination.java           CountDownLatch, Semaphore, CyclicBarrier
    UtilDemo.java               chạy mọi ví dụ và các lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/threads/util/
    UtilTest.java               chín test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.threads.util.UtilDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15), hai lần chạy liên tiếp cho cùng kết quả:

```text
fixed pool          = 9 tasks on a pool of 3: finished true, most running at once 3, distinct threads used 3
wrapped exception   = get threw ExecutionException whose cause is IllegalStateException: price service down
atomic counters     = expected 800000, AtomicInteger 800000, LongAdder 800000
semaphore           = 6 threads, 2 permits: at most 2 inside at once, permits back to 2
completable failure = recovered with -1, join threw CompletionException wrapping IllegalArgumentException
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
