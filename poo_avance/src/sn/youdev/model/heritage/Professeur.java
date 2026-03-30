package sn.youdev.model.heritage;

public class Professeur extends Personne {
    private String diplome;
    @Override
    public String toString(){
        return "Professeur diplome en "+diplome;
    }
    public Professeur(String diplome,String prenom,String telephone){
        super(prenom,telephone);
        this.diplome=diplome;
    }
    @Override
    public void afficher(){
        System.out.println("Prenom : "+prenom);
    }

    @Override
    public void communiquer() {

    }
}
