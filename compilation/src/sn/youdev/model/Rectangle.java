package sn.youdev.model;

public class Rectangle {
    private static int compteur = 0;

    private double largeur;
    private double hauteur;

    public Rectangle() {
        this(1, 1);
    }

    public Rectangle(double cote) {
        this(cote, cote);
    }

    public Rectangle(double largeur, double hauteur) {
        setLargeur(largeur);
        setHauteur(hauteur);
        compteur++;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        if (largeur <= 0) {
            throw new IllegalArgumentException("Largeur invalide");
        }
        this.largeur = largeur;
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setHauteur(double hauteur) {
        if (hauteur <= 0) {
            throw new IllegalArgumentException("Hauteur invalide");
        }
        this.hauteur = hauteur;
    }

    public double aire() {
        return largeur * hauteur;
    }

    public double perimetre() {
        return 2 * (largeur + hauteur);
    }

    public static int getCompteur() {
        return compteur;
    }
}
