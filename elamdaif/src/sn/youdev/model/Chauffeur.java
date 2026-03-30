package sn.youdev.model;

import java.util.Calendar;
import java.util.List;

public class Chauffeur {
    private final int id;
    private String prenom;
    private String nom;
    private String permis;
    private String etat;

    public int getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        Utils.verifierEnum(permis, List.of("A","B"));

        this.permis = permis;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        Utils.verifierEnum(etat, List.of("dispo","indispo"));

        this.etat = etat;
    }



    public Chauffeur(int id, String prenom, String nom, String permis, String etat) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        Utils.verifierEnum(permis, List.of("A","B"));

        this.permis = permis;
        Utils.verifierEnum(etat, List.of("dispo","indispo"));

        this.etat = etat;

    }
}
