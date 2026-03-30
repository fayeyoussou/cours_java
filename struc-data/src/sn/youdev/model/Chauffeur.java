package sn.youdev.model;

public class Chauffeur {
    private String nom;
    private String prenom;
    private String permis;
    private String etat;

    private void verifierPermis(String permis) {
        if(permis.isBlank()){
            throw new IllegalArgumentException("Type ne peut etre vide");
        }
        String lowerType = permis.toLowerCase();
        if(!lowerType.equals("A") && !lowerType.equals("B")){
            throw new IllegalArgumentException("Type doit etre soit A ou leger");
        }
    }
    public Chauffeur(String nom, String prenom, String permis, String etat) {
        this.nom = nom;
        this.prenom = prenom;
        verifierPermis(permis);
        this.permis = permis;
        Vehicule.verifierEtat(etat);
        this.etat = etat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
