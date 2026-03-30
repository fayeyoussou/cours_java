package sn.youdev.exemple.model;

public class Boite<T,S> {
    private T valeur;
    private S valeur2;
    public Boite(T valeur, S valeur2) {
        this.valeur = valeur;
        this.valeur2 = valeur2;
    }
}
