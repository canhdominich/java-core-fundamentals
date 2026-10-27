import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.Instant;

public class ZoneChallenge {
    public static void main(String[] args) {
        LocalDateTime wall = LocalDateTime.of(2026, 8, 14, 23, 30);
        // Gắn múi giờ rồi lấy mốc tuyệt đối
        Instant bought = wall.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant();
        System.out.println(bought);
        for (String zone : new String[] {"Asia/Ho_Chi_Minh", "Asia/Tokyo", "America/New_York"}) {
            ZonedDateTime z = bought.atZone(ZoneId.of(zone));
            System.out.println(zone + " " + z.toLocalDate() + " " + z.toLocalTime());
        }
    }
}
