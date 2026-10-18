# Bài 03 - Set và Map: hợp đồng equals và hashCode, các phép toán tập hợp, thống kê, TreeMap và bộ nhớ đệm LRU

Mã nguồn đi kèm bài học **Set và Map** (chương 12: Collections Framework) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ làm các phép hợp, giao, hiệu trên `Set`, dùng `TreeSet` để hỏi phần tử gần nhất, dùng `Map` (`put` trả giá trị cũ, `getOrDefault`, `merge` để đếm từ, `computeIfAbsent` để nhóm, `TreeMap` điều hướng, khung nhìn `keySet` và `values` ghi xuyên), thấy một lớp chỉ ghi đè `equals` mà thiếu `hashCode` làm `HashSet` và `HashMap` tìm không ra phần tử bằng nhau, đo chi phí khi nhiều khoá có cùng mã băm, và tự làm một bộ nhớ đệm LRU bằng `LinkedHashMap` chỉ với vài dòng.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
03-set-map/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/collections/setmap/
    BadPoint.java               chỉ ghi đè equals
    GoodPoint.java              ghi đè cả equals và hashCode
    ConstantHashKey.java        khoá mọi mã băm bằng nhau, có đếm equals
    LruCache.java               bộ nhớ đệm LRU dựa trên LinkedHashMap
    Sets.java                   phép toán tập hợp, TreeSet, hợp đồng equals và hashCode
    Maps.java                   thao tác Map, merge, computeIfAbsent, TreeMap, khung nhìn
    SetMapDemo.java             chạy mọi ví dụ
  src/test/java/dev/canhdominich/core/collections/setmap/
    SetMapTest.java             chín test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.collections.setmap.SetMapDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
set algebra     = a [1, 2, 3, 4], b [3, 4, 5], union [1, 2, 3, 4, 5], intersection [3, 4], a minus b [1, 2]
word count      = {be=2, not=1, or=1, to=2}
lost key        = equals only: get null | equals and hashCode: get home
LRU cache       = capacity 3, after touching a and adding d the cache holds [c, a, d] and b was evicted: true
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
