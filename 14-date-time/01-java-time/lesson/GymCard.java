import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public final class GymCard {
    private static final DateTimeFormatter SLASH = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final LocalDate bought;
    private final int months;

    public GymCard(LocalDate bought, int months) {
        this.bought = bought;
        this.months = months;
    }

    public LocalDate expiry() {
        // Cộng một lần từ ngày mua để giữ ngày 31
        return bought.plusMonths(months);
    }

    public GymCard renew(int moreMonths) {
        // Trả về thẻ mới, thẻ cũ không đổi
        return new GymCard(bought, months + moreMonths);
    }

    public long daysLeft(LocalDate today) {
        return ChronoUnit.DAYS.between(today, expiry());
    }

    public String label() {
        return "bought " + bought.format(SLASH) + ", expires " + expiry().format(SLASH);
    }

    public static void main(String[] args) {
        GymCard card = new GymCard(LocalDate.of(2026, 1, 31), 1);
        GymCard renewed = card.renew(1);
        LocalDate today = LocalDate.of(2026, 2, 10);
        System.out.println(card.label());
        System.out.println(renewed.label());
        System.out.println(card.daysLeft(today) + " " + renewed.daysLeft(today));
        System.out.println(card.expiry());
    }
}
