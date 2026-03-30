package sn.youdev.app;

import sn.youdev.interfaces.OperationBinaire;
import sn.youdev.interfaces.OperationBinaireNombre;
import sn.youdev.interfaces.VerificateurNombre;


public class Main {
    public static void main(String[] args) {
        VerificateurNombre estPaire = num -> num % 2 == 0;
        VerificateurNombre multipleDeTrois = num -> num % 3 == 0;
//        VerificateurNombre paireEtMultipleDeTrois = estPaire.et(multipleDeTrois);
        System.out.println(estPaire.verifier(5));
        System.out.println(multipleDeTrois.verifier(4));
//        System.out.println(paireEtMultipleDeTrois.verifier(18));
        OperationBinaire addition =  (gauche, droite) -> gauche + droite;
        OperationBinaire diviser =  (gauche, droite) -> gauche + droite;
        OperationBinaire fusionner = addition.puis(diviser);
        IO.println(diviser.calculer(addition.calculer(6,7),3));
        IO.println(fusionner.calculer(addition.calculer(6,7),3));
        OperationBinaireNombre<Float> additionnerFloat = (gauche, droite) -> gauche + droite;
        IO.println(additionnerFloat.calculer(4.7f,9.6f));


    }
}
