package sn.youdev.l2gl.model;

public class Vehicule extends Entite{
    private final String immatriculation;// (non vide)
    private final String marque;// (non vide)
    private int kilometrage;// (>=0)
    private EtatVehicule etat;//
    private final int annee;// (>=1990 par ex.)
    public String afficher() {
        return "";
    }
}
