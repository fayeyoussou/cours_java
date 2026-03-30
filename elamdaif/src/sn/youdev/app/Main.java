package sn.youdev.app;

import sn.youdev.interfaces.Comparateur;
import sn.youdev.interfaces.EnChaine;
import sn.youdev.interfaces.VerifierConformite;
import sn.youdev.model.Chauffeur;
import sn.youdev.model.Vehicule;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        VerifierConformite peutConduire = (v,c)->{
            return verifierPeutConduire(v, c);
        };
        VerifierConformite peutAllerEnMission = (v,c)->{
            if(verifierPeutConduire(v, c) && v.getEtat().equals("dispo") && c.getEtat().equals("dispo")){
                return true;
            }
            return false;
        };
        "Amadou".compareTo("PATHE");
        EnChaine<Chauffeur> chauffeurEnChaine =(c)->{
            return c.getPrenom();
        };
        Comparateur<Chauffeur> comparerChauffeur =(a,b)->{
           return a.getPrenom().compareTo(b.getPrenom());
        };
        VerifierConformite tester = (v,c)->{
            return  c.getPrenom().contains("a") && v.getMarque().contains("a");
        };
        Consumer<Chauffeur> commeVousVoulez =(c)->{
            System.out.println(c.getNom()+c.getPrenom());
        };


    }

    private static boolean verifierPeutConduire(Vehicule v, Chauffeur c) {
        if(c.getPermis().equals("A") || v.getType().equals("leger")){
            return true;
        }
        return false;
    }
}
