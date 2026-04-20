package sn.youdev.app;

import sn.youdev.exception.ServiceException;
import sn.youdev.file.EtudiantService;
import sn.youdev.model.Bilan;
import sn.youdev.model.Note;

import java.nio.file.Path;
import java.util.List;

// Démo : import CSV complet, bilan succès/échecs, top 3, parsing dates et nombres (slides 32-37)
public class MainImport {

    public static void main(String[] args) {
        importerGroupe(Path.of("data", "groupe1.csv"), "Groupe 1");
        importerGroupe(Path.of("data", "groupe2.csv"), "Groupe 2");
        importerGroupe(Path.of("data", "groupe3.csv"), "Groupe 3");
    }

    // Importe un groupe, affiche le bilan et les 3 meilleures notes
    static void importerGroupe(Path path, String label) {
        System.out.println("=== Import " + label + " ===");
        EtudiantService service = new EtudiantService();
        try {
            Bilan bilan = service.importer(path);
            bilan.afficher();
            afficherTop3(service.getNotes(), label);
            afficherMoyenne(service.getNotes(), label);
        } catch (ServiceException e) {
            // ServiceException wrappe l'IOException – on affiche le message métier seulement
            System.err.println("Erreur service : " + e.getMessage());
        }
        System.out.println();
    }

    // Trie les notes par valeur décroissante et affiche le podium
    static void afficherTop3(List<Note> notes, String label) {
        System.out.println("Top 3 " + label + " :");
        notes.stream()
             .sorted((a, b) -> Double.compare(b.getValeur(), a.getValeur()))
             .limit(3)
             .forEach(n -> System.out.println("  " + n));
    }

    // Calcule la moyenne du groupe avec un Stream de doubles
    static void afficherMoyenne(List<Note> notes, String label) {
        notes.stream()
             .mapToDouble(Note::getValeur)
             .average()
             .ifPresent(moy -> System.out.printf("Moyenne %s : %.2f%n", label, moy));
    }
}
