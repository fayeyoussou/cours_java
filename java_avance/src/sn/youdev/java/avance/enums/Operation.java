package sn.youdev.java.avance.enums;

public enum Operation {
    ADDITION("+") {
        @Override
        public double calculer(double a, double b) {
            return a + b;
        }
    },
    SOUSTRACTION("-") {
        @Override
        public double calculer(double a, double b) {
            return a - b;
        }
    },
    MULTIPLICATION("*") {
        @Override
        public double calculer(double a, double b) {
            return a * b;
        }
    },
    DIVISION("/") {
        @Override
        public double calculer(double a, double b) {
            if (b == 0) {
                throw new IllegalArgumentException("Division par zero");
            }
            return a / b;
        }
    };

    private final String symbole;

    Operation(String symbole) {
        this.symbole = symbole;
    }

    public String getSymbole() {
        return symbole;
    }

    public abstract double calculer(double a, double b);
}
