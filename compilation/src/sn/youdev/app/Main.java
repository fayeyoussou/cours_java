package sn.youdev.app;

import sn.youdev.model.*;



public class Main {
    public static void main(String[] args) {
       sn.youdev.model.Etudiant tete = new Etudiant("A01","Modou",null);
       Etudiant e1 = new Etudiant("A02","Fallou",null);
       tete.setSuivant(e1);
       Object a = new Object();

       System.out.println(Etudiant.getCompteur());
    }
}
