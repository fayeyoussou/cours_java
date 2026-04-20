package sn.youdev.app;

import sn.youdev.exception.NoteInvalideException;
import sn.youdev.model.Etudiant;
import sn.youdev.model.Module;
import sn.youdev.model.Note;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;

// Démo : écriture fichier avec Files.writeString, option APPEND, export CSV (slide 27)
public class MainWrite {

    public static void main(String[] args) {
        Path output = Path.of("data", "notes_export.csv");
        List<Note> notes = creerNotes();
        ecrireCsv(output, notes);
        ajouterLog(output);
        afficherContenu(output);
    }

    // Construit une liste de notes avec des étudiants des trois groupes
    static List<Note> creerNotes() {
        try {
            return List.of(
                new Note(new Etudiant("G1-013", "Aliou DIAGO"),           new Module("Java"), 17.0, LocalDate.of(2024, 3, 21)),
                new Note(new Etudiant("G1-016", "Maman Rokhaya DIEDHIOU"),new Module("Java"), 18.0, LocalDate.of(2024, 3, 25)),
                new Note(new Etudiant("G1-022", "Pape Lindor FALL"),      new Module("Java"), 18.5, LocalDate.of(2024, 4,  5)),
                new Note(new Etudiant("G2-026", "Amadou Diery SYLLA"),    new Module("Java"), 18.0, LocalDate.of(2024, 4,  5)),
                new Note(new Etudiant("G2-032", "Abdou Salam Niass"),     new Module("Java"), 17.0, LocalDate.of(2024, 4, 10)),
                new Note(new Etudiant("G3-003", "Aissatou BALDE"),        new Module("Java"), 18.0, LocalDate.of(2024, 3, 12)),
                new Note(new Etudiant("G3-005", "Demba BOMOU"),           new Module("BDD"),  18.5, LocalDate.of(2024, 3, 15)),
                new Note(new Etudiant("G3-011", "Awa DIEME"),             new Module("BDD"),  17.5, LocalDate.of(2024, 3, 18))
            );
        } catch (NoteInvalideException e) {
            System.err.println("Note invalide dans les données : " + e.getMessage());
            return List.of();
        }
    }

    // Files.writeString : crée ou écrase le fichier avec le contenu CSV complet
    static void ecrireCsv(Path path, List<Note> notes) {
        System.out.println("=== Écriture CSV ===");
        StringBuilder sb = new StringBuilder("matricule;nom;module;note;dateSaisie\n");
        for (Note n : notes) {
            sb.append(n.getEtudiant().getMatricule()).append(";")
              .append(n.getEtudiant().getNom()).append(";")
              .append(n.getModule()).append(";")
              .append(n.getValeur()).append(";")
              .append(n.getDateSaisie()).append("\n");
        }
        try {
            Files.writeString(path, sb.toString());
            System.out.println("Fichier créé : " + path);
        } catch (IOException e) {
            System.err.println("Erreur écriture : " + e.getMessage());
        }
        System.out.println();
    }

    // StandardOpenOption.APPEND : ajoute à la fin sans écraser le fichier existant
    static void ajouterLog(Path path) {
        System.out.println("=== Ajout log en fin de fichier ===");
        String log = "\n[INFO] Export généré le " + LocalDate.now();
        try {
            Files.writeString(path, log, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Log ajouté.");
        } catch (IOException e) {
            System.err.println("Erreur ajout log : " + e.getMessage());
        }
        System.out.println();
    }

    // Relit le fichier exporté pour vérifier ce qui a été écrit
    static void afficherContenu(Path path) {
        System.out.println("=== Contenu de " + path + " ===");
        try {
            System.out.println(Files.readString(path));
        } catch (IOException e) {
            System.err.println("Erreur lecture : " + e.getMessage());
        }
    }
}
