package sn.youdev.test;

public class Etudiant extends Personne{
    private double[] notes;
    public Etudiant(int id, String nom, String prenom,double[] matricule) {
        super( id,  nom,  prenom);
        this.notes = matricule;
    }

    public double[] getNotes() {
        return notes;
    }

    public void setNotes(double[] notes) {
        this.notes = notes;
    }
}
