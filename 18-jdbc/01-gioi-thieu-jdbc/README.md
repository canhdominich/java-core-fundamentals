# Bài 01 - Giới thiệu JDBC và JDBC Driver

Mã nguồn đi kèm bài học **Giới thiệu JDBC và JDBC Drivers** (chương 18: Kết nối cơ sở dữ liệu JDBC) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

## Yêu cầu môi trường

| Thành phần | Phiên bản | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Docker | bản gần đây | chạy MySQL 8.4 bằng `docker compose` |
| MySQL Connector/J | 26.7.0 | tải jar ở phần dưới |

## Cách chạy

Mỗi file trong `lesson/` là một chương trình đơn lẻ, chạy thẳng từ mã nguồn, không cần Maven hay biên dịch riêng.

```bash
cd lesson
java -cp mysql-connector-j-26.7.0.jar TênFile.java
```

## Chuẩn bị MySQL

Tải driver và bật MySQL (file `docker-compose.yml` nằm ở thư mục `18-jdbc/`, cổng 3307, database `shop`, user `app`, mật khẩu `app_pw`):

```bash
curl -fLO https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/26.7.0/mysql-connector-j-26.7.0.jar
cd .. && docker compose up -d && cd -
```

Chạy `bash setup.sh` trong `lesson/` để tạo lại bảng mẫu trước mỗi lần thử.

## Các file trong lesson/

- `DriverInfo.java`
- `DriverPeek.java`
- `ErrorTour.java`
- `FirstQuery.java`
- `PriceFilter.java`
- `ShopReport.java`
- `setup.sh`

Mỗi chương trình được giải thích từng dòng trong bài học tương ứng trên nền tảng.
