package sn.youdev.java.avance.model.record;

public record Temperature(double celsius) {
    // Un record peut avoir une méthode métier simple.
    public double toFahrenheit() {
        return (celsius * 9 / 5) + 32;
    }

    // Un record peut avoir une méthode statique de fabrique.
    public static Temperature fromFahrenheit(double fahrenheit) {
        return new Temperature((fahrenheit - 32) * 5 / 9);
    }
}
