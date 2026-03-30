package sn.youdev.test;

public class Etudiant {
    private static int compteur=0;
    public static int getCompteur(){
        return compteur;
    }
    public Etudiant(String matricule , String nom,Etudiant suivant){
        this.nom=nom;
        this.matricule=matricule;
        this.suivant=suivant;
    }
    private static int compteur=0;
    private Etudiant suivant;
    public void setSuivant(Etudiant suivant){
        this.suivant=suivant;
    }

    private String matricule;

    private String nom;


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    void afficher() {

        System.out.println(matricule + " - " + nom);

    }

}