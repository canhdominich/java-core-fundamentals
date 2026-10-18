package dev.canhdominich.core.oop.encapsulation;

public final class Temperature {

    private final double kelvin;

    private Temperature(double kelvin) {
        if (kelvin < 0) {
            throw new IllegalArgumentException(String.format("below absolute zero: %.2f K", kelvin));
        }
        this.kelvin = kelvin;
    }

    public static Temperature ofCelsius(double celsius) {
        return new Temperature(celsius + 273.15);
    }

    public double celsius() {
        return kelvin - 273.15;
    }

    public double fahrenheit() {
        return celsius() * 9 / 5 + 32;
    }
}
