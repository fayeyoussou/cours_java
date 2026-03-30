package sn.youdev.test;

import java.util.Objects;

public abstract class Personne {
    private final int id;
    private String nom;
    private String prenom;

    public Personne(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            IO.println("C'est le meme objet");
            return true;
        }
        if (!(o instanceof Personne personne)) {
            IO.println("Ce n'est pas une instance de personne ou de ses enfants");
            return false;
        }
        IO.println("C'est une instance personne mais pas exactement le meme object");
        if (id == personne.id) {
            IO.println("C'est une instance de la meme classe avec les memes id");
            return true;
        }
        IO.println("C'est une instance de la meme classe avec des id differents");
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int getId() {
        return id;
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
}
