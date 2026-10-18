# Bài 03 - Lớp Arrays: sắp xếp, tìm kiếm nhị phân, so sánh, asList và tốc độ

Mã nguồn đi kèm bài học **Thao tác mảng và lớp Arrays** (chương 4: Mảng) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy các phương thức của `Arrays`: sắp xếp toàn bộ hoặc một đoạn, `binarySearch` với giá trị trả về âm khi không tìm thấy và kết quả sai khi mảng chưa sắp xếp, `fill`, `equals`, `deepEquals`, `hashCode`, `mismatch`, `compare`; thấy `Arrays.asList` là một khung nhìn cố định kích thước ghi xuyên vào mảng và bẫy với mảng kiểu nguyên thủy; so sánh sắp xếp ổn định với `Comparator`; và đo `int[]` nhanh hơn `Integer[]` bao nhiêu lần khi sắp xếp hai triệu phần tử.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
03-lop-arrays/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/arraytools/
    ArraysTour.java             sort, binarySearch, fill, equals, deepEquals, mismatch, compare, toString
    AsListTraps.java            asList là khung nhìn, kích thước cố định, mảng nguyên thủy, List.of
    SortingChoices.java         sắp xếp ổn định, Comparator, giảm dần, lỗi biên dịch
    SortSpeed.java              đo int[] so với Integer[] với hai triệu phần tử
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/arraytools/
    ArrayToolsTest.java         mười hai test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.arraytools.ArraysTour
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.arraytools.AsListTraps
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.arraytools.SortingChoices
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.arraytools.SortSpeed
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15), các kết quả cố định:

```text
binarySearch       = find 30 -> 2, find 5 -> -1, find 35 -> -4, find 99 -> -6
unsorted search    = find 30 in an unsorted array -> -1
primitiveArray = from int[] the list size is 1, from Integer[] the list size is 3
stable sort by length = [c, e, bb, aa, dd, ff]
```

Các dòng thời gian của `SortSpeed` phụ thuộc máy (trên máy đo, `Integer[]` chậm hơn khoảng 5,6 lần).

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 12, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |
