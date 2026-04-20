package sn.youdev.java.avance.model.pojo;

import sn.youdev.java.avance.enums.Mention;

public class Etudiant {
    private String prenom;
    private String nom;
    private double note;
    private Mention mention;

    public Etudiant(String prenom, String nom, double note, Mention mention) {
        this.prenom = prenom;
        this.nom = nom;
        this.note = note;
        this.mention = mention;
    }

    public Mention getMention(){
        if(note < 12){
            return Mention.PASSABLE;
        }
        return Mention.BIEN;
    }
}
