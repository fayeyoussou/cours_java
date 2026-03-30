package sn.youdev.model;

import java.util.Objects;

public class Etudiant implements Comparable<Etudiant> {


    private final String matricule;
    private double note;

    @Override
    public String toString() {
        return "Matricule: " + matricule + ", Note: " + note;
    }

    public Etudiant(String matricule, double note) {
        this.matricule = matricule;
        this.note = note;
    }

    public String getMatricule() {
        return matricule;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Etudiant that)) return false;
        return Objects.equals(matricule, that.matricule);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(matricule);
    }

    @Override
    public int compareTo(Etudiant o) {
        return Double.compare(this.getNote(), o.getNote());
    }
}
