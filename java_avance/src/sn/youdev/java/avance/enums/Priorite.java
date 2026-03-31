package sn.youdev.java.avance.enums;

public enum Priorite {
    BASSE("Basse", 1),
    NORMALE("Normale", 2),
    HAUTE("Haute", 3),
    URGENTE("Urgente", 4);

    private final String libelle;
    private final int niveau;

    Priorite(String libelle, int niveau) {
        this.libelle = libelle;
        this.niveau = niveau;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getNiveau() {
        return niveau;
    }

    public boolean isUrgente() {
        return this == URGENTE;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
