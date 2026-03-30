package sn.youdev.app;

import sn.youdev.model.Etudiant;

import java.util.Arrays;
import java.util.Comparator;

public class MainDeux {
    public static void main(String[] args){
        Etudiant[] listEtudiants = new Etudiant[3];
        listEtudiants[0] = new Etudiant("A01",10);
        listEtudiants[1] = new Etudiant("A02",12);
        listEtudiants[2] = new Etudiant("A03",13);
        Comparator<Etudiant> ordreNote = (e1,e2)->{
            return (int) (e2.getNote() - e1.getNote());
        };
        Arrays.sort(listEtudiants, ordreNote);
        for(Etudiant e: listEtudiants){
            System.out.println(e);
        }
        Arrays.sort(listEtudiants);
        IO.println("=======");
        for(Etudiant e: listEtudiants){
            System.out.println(e);
        }

    }
}
