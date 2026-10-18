package dev.canhdominich.core.conditions;

public final class Shipping {

    private Shipping() {
    }

    public static int fee(String zone, double weightKg, boolean member) {
        if (weightKg <= 0) {
            throw new IllegalArgumentException("weight must be positive: " + weightKg);
        }
        int base = switch (zone) {
            case "HN", "HCM" -> 20_000;
            case "DN", "HP" -> 25_000;
            default -> 35_000;
        };
        int extra = weightKg > 2 ? (int) Math.ceil(weightKg - 2) * 5_000 : 0;
        int total = base + extra;
        return member && total > 40_000 ? total - 10_000 : total;
    }

    public static void main(String[] args) {
        System.out.println("fee(HN, 1.5, no member)  = " + fee("HN", 1.5, false));
        System.out.println("fee(HN, 4.2, no member)  = " + fee("HN", 4.2, false));
        System.out.println("fee(CT, 6.0, member)     = " + fee("CT", 6.0, true));
        System.out.println("fee(CT, 6.0, no member)  = " + fee("CT", 6.0, false));
        try {
            fee("HN", 0, false);
        } catch (IllegalArgumentException e) {
            System.out.println("fee(HN, 0, ...)          = " + e.getMessage());
        }
    }
}
