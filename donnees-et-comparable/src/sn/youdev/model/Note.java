package sn.youdev.model;

import java.util.Objects;

// Cette classe represente une note simple.
public class Note implements Comparable<Note> {
    private final int id;
    private String matiere;
    private double valeur;

    public Note(int id, String matiere, double valeur) {
        this.id = id;
        this.matiere = matiere;
        this.valeur = valeur;
    }

    public int getId() {
        return id;
    }

    public String getMatiere() {
        return matiere;
    }

    public double getValeur() {
        return valeur;
    }

    // L'ordre naturel ici est base sur la valeur de la note.
    @Override
    public int compareTo(Note autreNote) {
        return Double.compare(this.valeur, autreNote.valeur);
    }

    // Deux notes sont egales si elles ont le meme id.
    @Override
    public boolean equals(Object objet) {
        if (this == objet) {
            return true;
        }
        if (objet == null || getClass() != objet.getClass()) {
            return false;
        }
        Note note = (Note) objet;
        return id == note.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", matiere='" + matiere + '\'' +
                ", valeur=" + valeur +
                '}';
    }
}
