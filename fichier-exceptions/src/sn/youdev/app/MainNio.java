package sn.youdev.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

// Démo : NIO.2 – Path, Files.readString, readAllLines, Files.lines avec Stream (slides 25-28)
public class MainNio {

    public static void main(String[] args) {
        Path groupe1 = Path.of("data", "groupe1.csv");
        Path groupe2 = Path.of("data", "groupe2.csv");
        Path groupe3 = Path.of("data", "groupe3.csv");

//        lireEntierFichier(groupe1);
        lireParLignes(groupe2);
//        filtrerStream(groupe3);
//        demoErreursFichier(Path.of("data", "inexistant.csv"));
    }

    // Files.readString : lecture complète en mémoire – à éviter sur les gros fichiers
    static void lireEntierFichier(Path path) {
        System.out.println("=== readString – groupe1 ===");
        try {
            String contenu = Files.readString(path);
            // Affiche les 300 premiers caractères pour ne pas surcharger la console
            System.out.println(contenu);
        } catch (IOException e) {
            System.err.println("Erreur lecture : " + e.getMessage());
        }
        System.out.println();
    }

    // Files.readAllLines : toutes les lignes dans une List<String>
    static void lireParLignes(Path path) {
        System.out.println("=== readAllLines – groupe2 (5 premières) ===");
        try {
            List<String> lignes = Files.readAllLines(path);
            lignes.forEach(elements ->{
                String[] element = elements.split(";");
                IO.println(element[3]);
            });
            System.out.println("Total lignes : " + lignes.size());
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
        System.out.println();
    }

    // Files.lines + Stream : filtre les étudiants du module BDD avec note >= 12
    static void filtrerStream(Path path) {
        System.out.println("=== Stream filtré – groupe3 : BDD >= 12 ===");
        try (Stream<String> stream = Files.lines(path)) {
            stream.skip(1)
                  .filter(l -> !l.isBlank())
                  .filter(l -> l.contains(";BDD;"))
                  .filter(l -> {
                      String[] cols = l.split(";");
                      return cols.length == 5 && Double.parseDouble(cols[3]) >= 12.0;
                  })
                  .forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Erreur stream : " + e.getMessage());
        }
        System.out.println();
    }

    // Capture d'erreurs NIO spécifiques avant IOException générique (slide 28)
    static void demoErreursFichier(Path path) {
        System.out.println("=== Erreurs NIO spécifiques ===");
        try {
            Files.readAllLines(path);
        } catch (NoSuchFileException e) {
            // NoSuchFileException avant IOException : du plus spécifique au plus général
            System.err.println("Fichier introuvable : " + e.getFile());
        } catch (IOException e) {
            System.err.println("Erreur I/O générique : " + e.getMessage());
        }
        System.out.println();
    }
}
