package sn.youdev.app;

import sn.youdev.implementation.Etudiant;

import java.util.Objects;

public class MainQuatre {
    public static void main(String[] args) {
        IO.println(Objects.hashCode("Modou"));
        Etudiant etudiant = new Etudiant("AD","sf",2);
        etudiant.equals(etudiant);

    }
}
