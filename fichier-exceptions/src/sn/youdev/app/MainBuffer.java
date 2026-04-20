package sn.youdev.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// Démo : BufferedReader/Writer – lecture et copie ligne par ligne (slides 12, 29-30)
public class MainBuffer {

    public static void main(String[] args) {
//        afficherLignes(Path.of("data", "groupe1.csv"), 5);
        copierSansBlancs(Path.of("data", "groupe2.csv"), Path.of("data", "groupe2_propre.csv"));
//        compterEtudiants(Path.of("data", "groupe3.csv"));
    }

    // try-with-resources : BufferedReader fermé automatiquement à la sortie du bloc
    static void afficherLignes(Path path, int max) {
        System.out.println("=== BufferedReader – " + max + " premières lignes groupe1 ===");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String ligne;
            int count = 0;
            IO.println(reader.readLine());
            while ((ligne = reader.readLine()) != null ) {
                System.out.println(ligne);
                count++;
            }
        } catch (IOException e) {
            System.err.println("Erreur lecture : " + e.getMessage());
        }
        System.out.println();
    }

    // Copie avec BufferedReader + BufferedWriter : supprime les lignes vides
    static void copierSansBlancs(Path src, Path dst) {
        System.out.println("=== Copie sans lignes vides groupe2 → groupe2_propre ===");
        try (BufferedReader reader = Files.newBufferedReader(src);
             BufferedWriter writer = Files.newBufferedWriter(dst)) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (!ligne.isBlank()) {
                    writer.write(ligne);
                    writer.newLine();
                }
            }
            System.out.println("Copie terminée → " + dst);
        } catch (IOException e) {
            System.err.println("Erreur copie : " + e.getMessage());
        }
        System.out.println();
    }

    // Compte les étudiants sans charger tout le fichier en mémoire
    static void compterEtudiants(Path path) {
        System.out.println("=== Comptage étudiants groupe3 ===");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            long count = reader.lines().skip(1).filter(l -> !l.isBlank()).count();
            System.out.println("Nombre d'étudiants : " + count);
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
        System.out.println();
    }
}
