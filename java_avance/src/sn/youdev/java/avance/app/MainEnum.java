package sn.youdev.java.avance.app;

import sn.youdev.java.avance.enums.MentionEnumAvance;
import sn.youdev.java.avance.enums.Operation;
import sn.youdev.java.avance.enums.Priorite;
import sn.youdev.java.avance.enums.TypeModule;

public class MainEnum {
    public static void main(String[] args) {
        MentionEnumAvance mention = MentionEnumAvance.fromNote(15).orElse(MentionEnumAvance.NON_ADMIS);
        TypeModule module = TypeModule.CORE;
        Priorite priorite = Priorite.URGENTE;
        Operation operation = Operation.MULTIPLICATION;

        // values parcourt toutes les constantes.
        for (TypeModule type : TypeModule.values()) {
            IO.println("Module : " + type + " | coef = " + type.getCoef());
        }

        // valueOf lit une constante depuis son nom.
        IO.println("Depuis valueOf : " + TypeModule.valueOf("CORE"));

        // Une enum peut contenir des données.
        IO.println("Mention : " + mention + " | note min = " + mention.getNoteMin());

        // Une enum peut contenir des méthodes métier.
        IO.println("Module majeur : " + module.isMajor());

        // Une enum peut avoir un libellé personnalisé.
        IO.println("Priorité : " + priorite.getLibelle() + " | niveau = " + priorite.getNiveau());

        // Chaque constante peut avoir son propre comportement.
        IO.println("Calcul : 4 " + operation.getSymbole() + " 3 = " + operation.calculer(4, 3));
    }
}
