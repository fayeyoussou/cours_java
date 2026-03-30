package sn.youdev.model;

import java.util.Objects;

public class Personne {
    private final int id;
    private String prenom;
    private String nom;
    private Float note;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Personne{");
        sb.append("id=").append(id);
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", note='").append(note).append('\'');

        sb.append('}');
        return sb.toString();
    }

    public Personne(int id, String prenom, String nom,Float note) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Personne personne)) return false;
        return id == personne.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

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

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    public int compareTo(Personne o) {
        // si this vient en premier retourner valeur negative
        // si o vient en premier retourner valeur postive
        // sinn 0
        return this.nom.compareTo(o.nom);
    }
}
