package sn.youdev.model;

// Représente un étudiant identifié par son matricule et son nom complet
public class Etudiant {
    private final String matricule;
    private final String nom;

    public Etudiant(String matricule, String nom) {
        this.matricule = matricule;
        this.nom = nom;
    }

    public String getMatricule() { return matricule; }
    public String getNom() { return nom; }

    @Override
    public String toString() { return matricule + " - " + nom; }
}
