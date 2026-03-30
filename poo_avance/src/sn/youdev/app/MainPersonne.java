package sn.youdev.app;

import sn.youdev.model.heritage.Etudiant;
import sn.youdev.model.heritage.Personne;
import sn.youdev.model.heritage.Professeur;

public class MainPersonne {
    public static void main(String[] args) {
        Professeur p = new Professeur("Master","Modou","77 999 00 00");
        Etudiant p2 = new Etudiant("Aissatou","77 099 08 88","A01");
        Etudiant p3 = new Etudiant("Aissatou","77 099 08 88","A01");
        Etudiant p4 = new Etudiant("Aissatou","77 099 08 88","A01");
        System.out.println(p2.equals(p3) && p2.equals(p4));
        p2.equals(null);
        // [1-20]= 1,2,3,4,5



        // deux elements qui ont le meme equals a01 , a01
        // doivent hashcode doit etre le meme
        // deux elements qui ont le meme hashcode
        // peuvent avoir le meme equals

//        Personne p3 = new Personne("Mouhamed","Dieng");
    }
}
