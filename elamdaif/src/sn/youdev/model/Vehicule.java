package sn.youdev.model;

import java.util.Calendar;
import java.util.List;

public class Vehicule {
    private final String matriculation;
    private String marque;
    private String type;
    private String etat;


    public Vehicule(String matriculation, String marque, String type, String etat) {
        this.matriculation = matriculation;
        this.marque = marque;
        Utils.verifierEnum(type, List.of("lourd","leger"));
        this.type = type;
        Utils.verifierEnum(etat, List.of("dispo","indispo"));
        this.etat = etat;
    }

    public String getMatriculation() {
        return matriculation;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        Utils.verifierEnum(type, List.of("lourd","leger"));

        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        Utils.verifierEnum(etat, List.of("dispo","indispo"));

        this.etat = etat;
    }


}
