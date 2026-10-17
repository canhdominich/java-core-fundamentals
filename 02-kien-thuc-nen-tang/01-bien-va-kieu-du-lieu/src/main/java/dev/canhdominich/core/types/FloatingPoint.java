package dev.canhdominich.core.types;

import java.math.BigDecimal;

public class FloatingPoint {

    public static void main(String[] args) {
        System.out.println("0.1 + 0.2 (double)           = " + (0.1 + 0.2));
        System.out.println("0.1 + 0.2 == 0.3             = " + (0.1 + 0.2 == 0.3));
        System.out.println("0.1f + 0.2f (float)          = " + (0.1f + 0.2f));
        System.out.println("|0.1 + 0.2 - 0.3| < 1e-9     = " + (Math.abs(0.1 + 0.2 - 0.3) < 1e-9));
        System.out.println("BigDecimal 0.1 + 0.2         = " + new BigDecimal("0.1").add(new BigDecimal("0.2")));
        System.out.println("new BigDecimal(0.1)          = " + new BigDecimal(0.1));
        System.out.println("(float) 16_777_217           = " + (float) 16_777_217);
        System.out.println("1 / 3 (int)                  = " + (1 / 3));
        System.out.println("1 / 3.0                      = " + (1 / 3.0));
        System.out.println("1.0 / 0                      = " + (1.0 / 0));
        System.out.println("0.0 / 0                      = " + (0.0 / 0));
        System.out.println("NaN == NaN                   = " + (Double.NaN == Double.NaN));
        double total = 0;
        for (int i = 0; i < 10; i++) {
            total += 0.1;
        }
        System.out.println("ten times 0.1 (double)       = " + total + "  equals 1.0: " + (total == 1.0));
        BigDecimal exact = BigDecimal.ZERO;
        for (int i = 0; i < 10; i++) {
            exact = exact.add(new BigDecimal("0.1"));
        }
        System.out.println("ten times 0.1 (BigDecimal)   = " + exact + "  equals 1.0: " + (exact.compareTo(BigDecimal.ONE) == 0));
    }
}
