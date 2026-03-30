package sn.youdev.model;

import java.util.Objects;

// Cette classe represente une classe d'etudiants.
public class Classe implements Comparable<Classe> {
    private final String code;
    private String nom;
    private String niveau;
    private int effectif;

    public Classe(String code, String nom, String niveau, int effectif) {
        this.code = code;
        this.nom = nom;
        this.niveau = niveau;
        this.effectif = effectif;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public String getNiveau() {
        return niveau;
    }

    public int getEffectif() {
        return effectif;
    }

    // L'ordre naturel ici est base sur l'effectif.
    @Override
    public int compareTo(Classe autreClasse) {
        return Integer.compare(this.effectif, autreClasse.effectif);
    }

    // Deux classes sont egales si leur code est le meme.
    @Override
    public boolean equals(Object objet) {
        if (this == objet) {
            return true;
        }
        if (objet == null || getClass() != objet.getClass()) {
            return false;
        }
        Classe classe = (Classe) objet;
        return Objects.equals(code, classe.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Classe{" +
                "code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                ", niveau='" + niveau + '\'' +
                ", effectif=" + effectif +
                '}';
    }
}
