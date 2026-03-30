package sn.youdev.model;

public class Personne {
    private String nom;
    private String prenom;

    public Personne() {
        this("Inconnu", "Inconnu");
    }

    public Personne(String nom, String prenom) {
        setNom(nom);
        setPrenom(prenom);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Nom invalide");
        }
        this.nom = nom.trim();
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        if (prenom == null || prenom.isBlank()) {
            throw new IllegalArgumentException("Prenom invalide");
        }
        this.prenom = prenom.trim();
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }
}
