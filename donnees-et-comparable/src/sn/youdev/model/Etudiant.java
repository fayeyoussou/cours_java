package sn.youdev.model;

import java.util.Objects;

// Cette classe represente un etudiant.
public class Etudiant implements Comparable<Etudiant> {
    private final String matricule;
    private String nom;
    private double moyenne;

    public Etudiant(String matricule, String nom, double moyenne) {
        this.matricule = matricule;
        this.nom = nom;
        this.moyenne = moyenne;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public double getMoyenne() {
        return moyenne;
    }

    // L'ordre naturel d'un etudiant est son matricule.
    @Override
    public int compareTo(Etudiant autreEtudiant) {
        return this.matricule.compareTo(autreEtudiant.matricule);
    }

    // Deux etudiants sont egaux si leur matricule est le meme.
    @Override
    public boolean equals(Object objet) {
        if (this == objet) {
            return true;
        }
        if (objet == null || getClass() != objet.getClass()) {
            return false;
        }
        Etudiant etudiant = (Etudiant) objet;
        return Objects.equals(matricule, etudiant.matricule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricule);
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", moyenne=" + moyenne +
                '}';
    }
}
