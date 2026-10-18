# Bài 05 - Kế thừa: extends, super, ghi đè, ép kiểu, thành phần thay cho kế thừa

Mã nguồn đi kèm bài học **Kế thừa (Inheritance)** (chương 6: Lập trình hướng đối tượng (OOP)) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy lớp `Dog` và `Cat` kế thừa `Animal`: dùng thành viên được kế thừa, ghi đè và gọi `super`, thấy trường chọn theo kiểu khai báo trong khi phương thức chọn theo đối tượng thật, ép kiểu sai ném `ClassCastException`, mọi lớp đều nằm dưới `Object`, ví dụ lớp con của `HashSet` đếm mỗi phần tử hai lần còn bản dùng thành phần đếm đúng, và bảy thông báo lỗi biên dịch của kế thừa.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
05-ke-thua/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/oop/inheritance/
    Animal.java                 lớp cha có trường private, protected và public
    Dog.java                    lớp con ghi đè, gọi super, thêm fetch
    Cat.java                    lớp con ghi đè sound
    CountingHashSet.java        kế thừa HashSet để đếm số lần thêm
    CountingSet.java            cùng mục đích bằng thành phần
    InheritanceDemo.java        chạy mọi ví dụ và bảy lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/oop/inheritance/
    InheritanceTest.java        bảy test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.oop.inheritance.InheritanceDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
overriding          = Generic says ... | Rex says woof and wags its tail | Tom says meow |
fields are static   = viaAnimal.label animal, viaDog.label dog, ((Dog) viaAnimal).label dog, but sound() is woof either way
fragile base class  = inheritance counts 6 attempts for 3 elements, composition counts 3; both hold 3 and 3 elements
extend a final class      = [line 1: cannot inherit from final A]
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
