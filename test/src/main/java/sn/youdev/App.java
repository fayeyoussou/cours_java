package sn.youdev;

import com.ibm.icu.text.RuleBasedNumberFormat;
import sn.youdev.model.Etudiant;
import sn.youdev.services.CrudService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class App {
    private static final CrudService crudService = new CrudService();

    public static void main(String[] args) throws IOException {
        System.out.println("=============>");
        System.out.println("Hello World!");
        System.out.println("=============>");
        System.out.println("\n--- NumberToLetterUtils (Franc CFA) ---");
        long[] montants = { 0, 1, 15, 71, 80, 90, 100, 200, 215, 1_000, 1_500, 10_000,
                75_000, 100_000, 500_000, 1_000_000, 2_500_000, 1_000_000_000L };
        RuleBasedNumberFormat rbnf =
                new RuleBasedNumberFormat(Locale.FRENCH, RuleBasedNumberFormat.SPELLOUT);

        for (long montant : montants) {
            System.out.println(montant + " = " + rbnf.format(montant) + " Frs CFA");
        }

        System.out.println(rbnf.format(21));
        System.out.println(rbnf.format(85));
        System.out.println(rbnf.format(2026));

        System.out.println("\n--- Liste des étudiants ---");
        List<Etudiant> etudiants = crudService.findAll();
        System.out.printf("%-10s %-45s %-6s %-6s %-12s%n", "Matricule", "Nom", "Module", "Note", "Date Saisie");
        System.out.println("-".repeat(85));
        for (Etudiant e : etudiants) {
            System.out.printf("%-10s %-45s %-6s %-6.1f %-12s%n",
                    e.getMatricule(), e.getNom(), e.getModule(), e.getNote(), e.getDateSaisie());
        }
        System.out.println("Total : " + etudiants.size() + " étudiant(s)");
    }
}
