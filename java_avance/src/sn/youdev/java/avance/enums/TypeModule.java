package sn.youdev.java.avance.enums;

public enum TypeModule {
    // Les constantes peuvent appeler le constructeur.
    CORE(5),
    OPTIONAL(2);

    private final int coef;

    TypeModule(int coef) {
        this.coef = coef;
    }

    public int getCoef() {
        return coef;
    }

    public boolean isMajor() {
        return coef > 3;
    }
}
