package sn.youdev.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Point d'entrée : assigne une note aléatoire à chaque étudiant et réécrit les 3 CSV
public class Main {

    private static final Random RNG = new Random();

    public static void main(String[] args) {
        Path[] fichiers = {
            Path.of("data", "groupe1.csv"),
            Path.of("data", "groupe2.csv"),
            Path.of("data", "groupe3.csv")
        };

        for (Path fichier : fichiers) {
            assignerNotesAleatoires(fichier);
        }
    }

    // Lit le CSV, remplace la note de chaque ligne par un tirage aléatoire [0.0 – 20.0], réécrit
    static void assignerNotesAleatoires(Path path) {
        System.out.println("=== Assignation notes → " + path + " ===");

        List<String> lignesModifiees = new ArrayList<>();
        String entete;

        // Lecture : conserve l'en-tête intact et collecte les lignes à modifier
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            entete = reader.readLine();
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (ligne.isBlank()) continue;
                lignesModifiees.add(remplacerNote(ligne));
            }
        } catch (IOException e) {
            System.err.println("Erreur lecture " + path + " : " + e.getMessage());
            return;
        }

        // Réécriture complète du fichier avec les nouvelles notes
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(entete);
            writer.newLine();
            for (String ligne : lignesModifiees) {
                writer.write(ligne);
                writer.newLine();
            }
            System.out.println("  " + lignesModifiees.size() + " étudiants mis à jour.");
        } catch (IOException e) {
            System.err.println("Erreur écriture " + path + " : " + e.getMessage());
        }
        System.out.println();
    }

    // Remplace la colonne note (index 3) par une valeur aléatoire et met la date à aujourd'hui
    private static String remplacerNote(String ligne) {
        String[] cols = ligne.split(";");
        double note = Math.round(RNG.nextDouble(0.0, 20.0) * 10.0) / 10.0;
        cols[3] = String.valueOf(note);
        cols[4] = LocalDate.now().toString();
        return String.join(";", cols);
    }
}
