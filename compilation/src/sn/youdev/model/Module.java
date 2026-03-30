package sn.youdev.model;

public class Module {
    private String code;
    private String libelle;
    private double coefficient;

    public Module(String code, String libelle) {
        this(code, libelle, 1.0);
    }

    public Module(String code, String libelle, double coefficient) {
        setCode(code);
        setLibelle(libelle);
        setCoefficient(coefficient);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Code module invalide");
        }
        this.code = code.trim().toUpperCase();
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        if (libelle == null || libelle.isBlank()) {
            throw new IllegalArgumentException("Libelle module invalide");
        }
        this.libelle = libelle.trim();
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        if (coefficient <= 0) {
            throw new IllegalArgumentException("Coefficient doit etre > 0");
        }
        this.coefficient = coefficient;
    }
}
