package sn.cours;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import sn.cours.model.Etudiant;
import sn.cours.model.Module;
import sn.cours.model.Note;
import sn.cours.util.NumberToLetterUtils;

public class Main {

    public static void main(String[] args) {

        // -------------------------------------------------------
        // DEMO 1 : Lombok @Data + @AllArgsConstructor
        // -------------------------------------------------------
        Etudiant e1 = new Etudiant(1L, "ET001", "Diop", "Amadou", 20);
        System.out.println(e1); // toString() généré par Lombok

        // Lombok génère aussi les setters
        e1.setAge(21);
        System.out.println("Age modifié : " + e1.getAge());

        // -------------------------------------------------------
        // DEMO 2 : Lombok @Builder
        // -------------------------------------------------------
        Module m1 = Module.builder()
                .id(1L)
                .code("INF201")
                .libelle("Programmation Java")
                .coefficient(3)
                .build();
        System.out.println(m1);

        Note note = Note.builder()
                .id(1L)
                .valeur(15.5)
                .etudiant(e1)
                .module(m1)
                .build();
        System.out.println(note);

        // -------------------------------------------------------
        // DEMO 3 : Bibliothèque externe via Maven (Apache Commons Lang)
        // -------------------------------------------------------
        System.out.println("\n--- Apache Commons Lang (StringUtils) ---");
        String nom = "  amadou diop  ";
        System.out.println("Rembourré 20 cars : '" + StringUtils.rightPad(StringUtils.trim(nom), 20, '.') + "'");
        System.out.println("Est vide ?        : " + StringUtils.isBlank(""));
        System.out.println("Est vide ?        : " + StringUtils.isBlank("bonjour"));


        System.out.println("'42' est un nombre: " + NumberUtils.isCreatable("42"));
        System.out.println("'abc' est un nombre: " + NumberUtils.isCreatable("abc"));
        System.out.println("parseInt sécurisé : " + NumberUtils.toInt("abc", -1)); // retourne -1 si invalide

        // -------------------------------------------------------
        // DEMO 4 : Conversion nombre → lettres Franc CFA
        // -------------------------------------------------------
        System.out.println("\n--- NumberToLetterUtils (Franc CFA) ---");
        long[] montants = { 0, 1, 15, 71, 80, 90, 100, 200, 215, 1_000, 1_500, 10_000,
                            75_000, 100_000, 500_000, 1_000_000, 2_500_000, 1_000_000_000L };
        for (long montant : montants) {
            System.out.printf("%,15d → %s%n", montant, NumberToLetterUtils.convert(montant));
        }
    }
}
