import java.util.Calendar;
import java.util.Date;

public class Legacy {
    public static void main(String[] args) {
        Date d = new Date(0L);
        Date alias = d;
        // Date đổi được qua tên khác
        alias.setTime(86_400_000L);
        System.out.println(d.getTime() + " " + (d == alias));

        Calendar c = Calendar.getInstance();
        c.clear();
        // Tháng 1 của Calendar là 0
        c.set(2026, 0, 1);
        System.out.println(c.get(Calendar.MONTH));

        c.clear();
        // Calendar nhận ngày 30/2 rồi cuộn sang tháng sau
        c.set(2026, Calendar.FEBRUARY, 30);
        System.out.println((c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH));

        System.out.println(new Date(1_786_000_000_000L).toInstant());
    }
}
