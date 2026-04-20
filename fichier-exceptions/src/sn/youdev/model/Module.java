package sn.youdev.model;

// Représente un module d'enseignement (Java, Web, BDD...)
public class Module {
    private final String nom;

    public Module(String nom) { this.nom = nom; }

    public String getNom() { return nom; }

    @Override
    public String toString() { return nom; }
}
