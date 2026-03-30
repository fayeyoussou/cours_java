package sn.youdev.app;

import sn.youdev.implementation.Etudiant;
import sn.youdev.interfaces.ActionMessage;
import sn.youdev.interfaces.OperationBinaire;
import sn.youdev.interfaces.VerificateurNombre;
import sn.youdev.interfaces.VerifierNote;

import java.util.function.Function;
import java.util.function.Predicate;

public class MainTrois {
    static void verifier(int x, Predicate<Integer> regle) {
        if (!regle.test(x)) {
            throw new IllegalArgumentException("Règle non respectée: " + x);
        }
        System.out.println("La valeur "+x+" respecte la regle");
    }

    public static void main(String[] args) {
        OperationBinaire add = (x,y)->x+y;
        OperationBinaire multi = (x,y)->x*y;
        OperationBinaire fussion = add.puis(multi);

    }
}
