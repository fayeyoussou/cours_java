package sn.youdev.java.avance.app;

import sn.youdev.java.avance.enums.Mention;

public class MainAvecSwitch {
    public static void main(String[] args) {
        Mention mention = Mention.BIEN;
        switchSimple(mention);
        switchLamda(mention);
        switchYield(mention);
    }

    private static void switchYield(Mention mention) {
        String  message = switch  (mention) {
            case  TRES_BIEN, BIEN  -> "Félicitations !"; // Cas simple
            case  PASSABLE  -> {
                IO.println("Alerte : limite"); // Logique supplémentaire
                 yield  "Peut mieux faire";   // Valeur retournée par le bloc
            }
            default  -> "Non admis";
        };
        IO.println(message);
    }

    private static void switchLamda(Mention mention) {
        int bonus = switch (mention) {
            case TRES_BIEN -> 100;
            case BIEN -> 50;
            case ASSEZ_BIEN -> 20;
            default -> 0;
        };
        // Point-virgule OBLIGATOIRE ici (fin d'instruction)
        IO.println("Bonus : " + bonus);
    }

    private static void switchSimple(Mention mention) {
        switch (mention) { // mention est de type Mention
            case PASSABLE: // ⚠  Pas de Mention.PASSABLE ici !
                IO.println("Peut mieux faire");
                break;     // ⚠  Important : sortir du switch
            case BIEN:
                IO.println("Bon travail");
                break;
            default:       // Gère les autres cas.
                IO.println("Autre résultat");
        }
    }
}
