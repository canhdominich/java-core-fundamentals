import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Zones {
    public static void main(String[] args) {
        Instant moment = Instant.parse("2026-08-14T02:30:00Z");
        // Cùng một mốc, hai cách viết theo múi giờ
        ZonedDateTime hcm = moment.atZone(ZoneId.of("Asia/Ho_Chi_Minh"));
        ZonedDateTime ny = moment.atZone(ZoneId.of("America/New_York"));
        System.out.println(moment);
        System.out.println(hcm.toLocalDateTime() + " " + hcm.getOffset());
        System.out.println(ny.toLocalDateTime() + " " + ny.getOffset());
        // Hai giá trị cùng chỉ một mốc thì so sánh bằng true
        System.out.println(hcm.toInstant().equals(ny.toInstant()));

        // LocalDateTime chưa có múi giờ nên chưa là một mốc
        LocalDateTime wall = LocalDateTime.of(2026, 8, 14, 9, 30);
        System.out.println(wall.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
        System.out.println(wall.atZone(ZoneId.of("UTC")).toInstant());
    }
}
