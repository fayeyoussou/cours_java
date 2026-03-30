package sn.youdev.app;

import sn.youdev.interfacefonctionnelle.VerifierConformite;
import sn.youdev.model.Chauffeur;
import sn.youdev.model.Vehicule;

public class MainIF {
    public static void main(String[] args) {
        VerifierConformite peutConduire = (v,c)-> verifierPeutConduire(v, c);
        ;
    }

    private static boolean verifierPeutConduire(Vehicule v, Chauffeur c) {
        if(c.getPermis() == A || v.getType() == "leger"){
            return true;
        }else {
            return false;
        }
    }
}
