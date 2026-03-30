package sn.youdev.model;

public class Vehicule {
    private final String matricule;
    private String marque;
    private String Type;
    private String etat;

    private void verifierType(String type){
        if(type.isBlank()){
            throw new IllegalArgumentException("Type ne peut etre vide");
        }
        String lowerType = type.toLowerCase();
        if(!lowerType.equals("lourd") && !lowerType.equals("leger")){
            throw new IllegalArgumentException("Type doit etre soit lourd ou leger");
        }
    }
    protected static void verifierEtat(String etat){
        if(etat.isBlank()){
            throw new IllegalArgumentException("Type ne peut etre vide");
        }
        String lowerType = etat.toLowerCase();
        if(!lowerType.equals("dispo") && !lowerType.equals("indisponible")){
            throw new IllegalArgumentException("Type doit etre soit lourd ou leger");
        }
    }
    public Vehicule(String matricule, String marque, String type, String etat) {
        this.matricule = matricule;
        this.marque = marque;
        verifierType(type);
        Type = type;
        verifierEtat(etat);
        this.etat = etat;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getType() {

        return Type;
    }

    public void setType(String type) {
        verifierType(type);
        Type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        verifierEtat(etat);
        this.etat = etat;
    }
}
