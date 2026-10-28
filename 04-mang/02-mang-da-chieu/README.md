# Bài 02 - Mảng đa chiều: ma trận, mảng răng cưa, sao chép nông và thứ tự duyệt

Mã nguồn đi kèm bài học **Mảng đa chiều** (chương 4: Mảng) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy các phép toán ma trận (cộng, chuyển vị, nhân, tổng từng hàng), dựng tam giác Pascal bằng mảng răng cưa, đếm hàng xóm trong lưới có kiểm tra biên, tái hiện lỗi `Arrays.fill` làm mọi hàng là cùng một đối tượng, thấy `clone` của mảng hai chiều chỉ sao chép nông, đo chênh lệch tốc độ giữa duyệt theo hàng và duyệt theo cột trên ma trận 6000 x 6000, và đọc sáu thông báo lỗi biên dịch về mảng đa chiều.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
02-mang-da-chieu/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/matrix/
    Matrices.java               ma trận, tam giác Pascal, hàng xóm, lỗi dùng chung hàng, sao chép
    TraversalOrder.java         đo duyệt theo hàng so với theo cột
    MatrixErrors.java           sáu lỗi biên dịch về mảng đa chiều
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/matrix/
    MatrixTest.java             mười hai test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.matrix.Matrices
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.matrix.TraversalOrder
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.matrix.MatrixErrors
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15), các con số cố định:

```text
multiply 2x3 by 3x2  = [[22, 28], [49, 64]]
pascal(5)            = [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1]]
sharedRowBug         = [[1, 0, 0], [1, 0, 0], [1, 0, 0]], all rows are one object: true
shallowClone         = original [[99, 2], [3, 4]], same row object: true
```

Các dòng thời gian của `TraversalOrder` phụ thuộc máy (trên máy đo, duyệt theo cột chậm hơn khoảng 26 lần), nhưng duyệt theo cột luôn chậm hơn rõ rệt.

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

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Breakages`, `ClassGrades`, `Extras`, `GradeBook`, `Selfcheck`.
