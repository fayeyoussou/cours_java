package sn.youdev.java.avance.app;

import sn.youdev.java.avance.enums.*;
import sn.youdev.java.avance.interfaces.EnEntier;
import sn.youdev.java.avance.model.record.Professeur;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class MainEnum {
    public static void main(String[] args) {
        Professeur p = null;
//        Objects.requireNonNull(p,"Le professeur ne peut pas etre null");
        LocalDate rentree  =  LocalDate.of(2026,  2,  31);
        //  Septembre  =  9

        IO.println(rentree.toString());
        Optional<Mention> mamention = Mention.fromNote(-1f);
        mamention.ifPresentOrElse((x)->IO.println(x.name()),()->{
            IO.println("On a rien trouvé");
        });
//        float voir = switch (mamention){
//            case AJOURNEE -> 0f;
//            case PASSABLE -> {
//                IO.println("PASSABLE");
//                yield Mention.PASSABLE.getMinValue();
//            }
//            default ->{
//                IO.println("inconnue");
//                yield 0f;
//            }
//        };
//        IO.println(mamention);
//
//        for (Mention m : Mention.values()){
//            IO.println("Max "+m.getMaxValue());
//            IO.println("Min "+m.getMinValue());
//            IO.println(m.name());
//
//        }
//        EnEntier voirMin = m-> {
//            IO.println("Min "+m.getMinValue());
//            return m.getMinValue();
//        };
//        EnEntier somme = p-> {
//            IO.println(p.getX() + " " + p.getY());
//            return p.getX() + p.getY();
//        };
//
//         TypeConteneur quarante = TypeConteneur.valueOf("QUARANTE");
//         for (TypeConteneur t : TypeConteneur.values()) {
//             IO.print(t.getPied());
//             IO.println(t.name());
//         }
//         int pied =  switch (quarante) {
//             case VINGT -> 0;
//             case QUARANTE -> 40;
//        };
//        MentionEnumAvance mention = MentionEnumAvance.fromNote(15).orElse(MentionEnumAvance.NON_ADMIS);
//        TypeModule module = TypeModule.CORE;
//        Priorite priorite = Priorite.URGENTE;
//        Operation operation = Operation.MULTIPLICATION;
//
//        // values parcourt toutes les constantes.
//        for (TypeModule type : TypeModule.values()) {
//            IO.println("Module : " + type + " | coef = " + type.getCoef());
//        }
//
//        // valueOf lit une constante depuis son nom.
//        IO.println("Depuis valueOf : " + TypeModule.valueOf("CORE"));
//
//        // Une enum peut contenir des données.
//        IO.println("Mention : " + mention + " | note min = " + mention.getNoteMin());
//
//        // Une enum peut contenir des méthodes métier.
//        IO.println("Module majeur : " + module.isMajor());
//
//        // Une enum peut avoir un libellé personnalisé.
//        IO.println("Priorité : " + priorite.getLibelle() + " | niveau = " + priorite.getNiveau());
//
//        // Chaque constante peut avoir son propre comportement.
//        IO.println("Calcul : 4 " + operation.getSymbole() + " 3 = " + operation.calculer(4, 3));
    }
}
