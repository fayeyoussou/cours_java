package sn.youdev.app;

import sn.youdev.exception.EtudiantInexistantException;
import sn.youdev.exception.NoteInvalideException;
import sn.youdev.file.EtudiantService;
import sn.youdev.model.Etudiant;
import sn.youdev.model.Module;
import sn.youdev.model.Note;

import java.time.LocalDate;

// Démo : try/catch/finally, multi-catch et exceptions personnalisées (slides 11-22)
public class MainException {

    public static void main(String[] args) {
        demoParsageNote();
        demoNoteInvalide();
        demoMultiCatch();
        demoEtudiantInexistant();
        demoFinally();
    }

    // try/catch/finally : parsage d'une note textuelle avec bloc de nettoyage garanti
    static void demoParsageNote() {
        System.out.println("=== try / catch / finally ===");
        String input = "vingt";
        try {
            System.out.println("Parsage en cours...");
            int note = Integer.parseInt(input);
            System.out.println("Note validée : " + note);
        } catch (NumberFormatException e) {
            System.err.println("Erreur : pas un entier → " + e.getMessage());
        } finally {
            // finally s'exécute toujours, même si une exception a été levée
            System.out.println("Fin du parsage.\n");
        }
    }

    // NoteInvalideException : exception checked personnalisée avec champ métier
    static void demoNoteInvalide() {
        System.out.println("=== NoteInvalideException ===");
        try {
            Etudiant etudiant = new Etudiant("G1-009", "Adji Farimata CISSE");
            Note note = new Note(etudiant, new Module("BDD"), -5.0, LocalDate.of(2024, 3, 18));
            System.out.println("Note créée : " + note);
        } catch (NoteInvalideException e) {
            System.err.println("Refusée : " + e.getMessage());
            System.err.println("Valeur rejetée : " + e.getValeurRefusee());
        }
        System.out.println();
    }

    // Multi-catch : plusieurs exceptions capturées séparément du plus spécifique au général
    static void demoMultiCatch() {
        System.out.println("=== Multi-catch ===");
        try {
            Etudiant etudiant = new Etudiant("G1-022", "Pape Lindor FALL");
            // Note > 20 pour déclencher NoteInvalideException
            Note note = new Note(etudiant, new Module("Java"), 25.0, LocalDate.of(2024, 4, 5));
            System.out.println(note);
        } catch (NoteInvalideException e) {
            System.err.println("Note hors limite : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e);
        }
        System.out.println();
    }

    // EtudiantInexistantException : ne jamais retourner null, toujours lever une exception
    static void demoEtudiantInexistant() {
        System.out.println("=== EtudiantInexistantException ===");
        EtudiantService service = new EtudiantService();
        try {
            Etudiant e = service.trouverParMatricule("G1-999");
            System.out.println("Trouvé : " + e);
        } catch (EtudiantInexistantException e) {
            System.err.println(e.getMessage());
        }
        System.out.println();
    }

    // finally avec ressource manuelle : pattern legacy avant Java 7 (try-with-resources)
    static void demoFinally() {
        System.out.println("=== Pattern legacy finally ===");
        java.io.BufferedReader br = null;
        try {
            br = new java.io.BufferedReader(
                    new java.io.FileReader("data/groupe1.csv"));
            System.out.println("Première ligne : " + br.readLine());
        } catch (java.io.IOException e) {
            System.err.println("Erreur lecture : " + e.getMessage());
        } finally {
            // Fermeture manuelle obligatoire dans le finally pour garantir la libération
            if (br != null) {
                try { br.close(); } catch (java.io.IOException ignored) {}
            }
            System.out.println("Ressource fermée.\n");
        }
    }
}
