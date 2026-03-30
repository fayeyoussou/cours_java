package sn.youdev.model.heritage;

import java.util.Objects;

public final class Etudiant extends Personne {
    private final String matricule;
    private int age;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Etudiant etudiant = (Etudiant) o;
        return age == etudiant.age
                && Objects.equals(matricule, etudiant.matricule)
                && Objects.equals(prenom, etudiant.prenom)
                && Objects.equals(nom, etudiant.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricule, age, prenom, nom);
    }

    public Etudiant(String prenom, String nom, String telephone, int age, String matricule){
        // Super c'est toujours le parent le plus proche
        // super c'est toujours le constructeur du parent
        super(prenom, nom, telephone);
        //  reference a la classe elle
        // this avec c'est toujours le constructeur de la classe elle meme
        this.age = age;
        this.matricule = matricule;
    }
    public Etudiant(String prenom,String telephone,String matricule){
        super(prenom, telephone);
        this.age = 18;
        this.matricule = matricule;
    }

    @Override
    public void afficher() {
        System.out.println("Matricule : "+matricule + " Age :"+age);

    }
    public final void voirNote(){
        System.out.println("Voir note");
    }

    @Override
    public void communiquer() {

    }
}
