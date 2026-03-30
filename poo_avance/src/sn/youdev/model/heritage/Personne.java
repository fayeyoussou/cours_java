package sn.youdev.model.heritage;

public  abstract class Personne implements Comportement {
    protected String prenom;
    protected String nom;
    protected String telephone;
    private String getPrenom(){
        return prenom;
    }
    public abstract void afficher();
    private void setPrenom(String prenom){
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Personne(String prenom, String nom, String telephone){
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
    }
    public Personne(String prenom,  String telephone){
        this(prenom,"FAYE",telephone);
    }
}
