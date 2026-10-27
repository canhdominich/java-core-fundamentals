# Bài 02 - List: ArrayList và LinkedList, thao tác, chi phí và xoá phần tử khi đang duyệt

Mã nguồn đi kèm bài học **List: ArrayList và LinkedList** (chương 12: Collections Framework) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ dùng `add`, chèn theo chỉ số, `set`, `indexOf`, phân biệt `remove(int)` với `remove(Object)`, đọc lỗi chỉ số, thấy `equals` của `List` so sánh phần tử theo thứ tự, `subList` là khung nhìn dùng chung dữ liệu, `ListIterator` đi ngược, và `Arrays.asList` có kích thước cố định. Sau đó đo `ArrayList` so với `LinkedList` (đọc theo chỉ số, chèn đầu, thêm cuối, duyệt) và thử sáu cách xoá phần tử khi đang duyệt, hai cách sai và bốn cách đúng.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
02-list/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/collections/list/
    ListOps.java                thao tác cơ bản, remove theo chỉ số, subList, ListIterator
    Removal.java                sáu cách xoá khi đang duyệt
    Speed.java                  đo ArrayList so với LinkedList
    ListDemo.java               chạy mọi ví dụ
  src/test/java/dev/canhdominich/core/collections/list/
    ListTest.java               tám test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.collections.list.ListDemo
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.collections.list.Speed
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15), các kết quả cố định:

```text
remove(int)       = remove(1) removes the element at index 1: [10, 30, 1], remove(Integer.valueOf(1)) removes the value 1: [10, 30]
remove in for-each= ConcurrentModificationException with message null, the list is now [1, 3, 4, 5, 6]
removeIf          = removeIf gives [1, 3, 5] and reports changed true
```

Các dòng thời gian của `Speed` phụ thuộc máy (trên máy đo, đọc theo chỉ số 50000 phần tử mất hơn một giây với `LinkedList` và gần như bằng không với `ArrayList`; chèn 50000 phần tử vào đầu thì ngược lại).

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 8, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ của bài theo khung mới, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `CafeOrders` (ví dụ đầu tiên), `KitchenBoard` (làm cùng mình), `Skip` và `Breakages` (lỗi thường gặp), `Trace`, `MoveToFront`, `OrderBoard`, `Bench` (bài tập) và `Selfcheck` (tự kiểm tra).
