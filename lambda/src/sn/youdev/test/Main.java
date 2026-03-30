package sn.youdev.test;


public class Main {


    public static void main(String[] args) {
        Etudiant etudiant = new Etudiant(1,"FAYE","Youssouha", new double[]{12,12.5});
        Etudiant etudiant2 = new Etudiant(1,"FAYE","Youssouha", new double[]{12,12.5});
        IO.println(etudiant.equals(etudiant2));


    }
}
