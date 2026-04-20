package sn.youdev.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

// Démo : try-with-resources sur fichiers – fermeture automatique de toute ressource AutoCloseable
public class MainTryResources {

    public static void main(String[] args) {
        lireUnFichier();
        lireAvecStream();
        ecrireDansFichier();
        lireEtEcrireEnMemeTemps();
        ouvrirPlusieursRessources();
        exporterAdmis();
        trierAdmisParNote();
    }

    // Cas de base : BufferedReader fermé automatiquement même si readLine() lève une exception
    static void lireUnFichier() {
        System.out.println("=== Lecture simple – groupe1 ===");
        try (BufferedReader reader = Files.newBufferedReader(Path.of("data", "groupe1.csv"))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                System.out.println(ligne);
            }
        } catch (IOException e) {
            System.err.println("Erreur lecture : " + e.getMessage());
        }
        System.out.println();
    }

    // Stream de lignes : Files.lines() implémente AutoCloseable – doit être dans le try(...)
    static void lireAvecStream() {
        System.out.println("=== Stream de lignes – groupe2 ===");
        try (Stream<String> lignes = Files.lines(Path.of("data", "groupe2.csv"))) {
            lignes.skip(1)
                  .filter(l -> !l.isBlank())
                  .forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Erreur stream : " + e.getMessage());
        }
        System.out.println();
    }

    // BufferedWriter fermé automatiquement : flush() garanti même en cas d'exception
    static void ecrireDansFichier() {
        System.out.println("=== Écriture – resume_groupe3.csv ===");
        Path out = Path.of("data", "resume_groupe3.csv");
        try (BufferedWriter writer = Files.newBufferedWriter(out,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write("matricule;nom;note");
            writer.newLine();
            // Quelques étudiants du groupe 3 en dur pour la démo
            String[] lignes = {
                "G3-003;Aissatou BALDE;18.0",
                "G3-005;Demba BOMOU;18.5",
                "G3-011;Awa DIEME;17.5",
                "G3-016;Adja Diagne GUEYE;17.0",
                "G3-028;Faty NDAO;17.0"
            };
            for (String l : lignes) {
                writer.write(l);
                writer.newLine();
            }
            System.out.println("Fichier écrit : " + out);
        } catch (IOException e) {
            System.err.println("Erreur écriture : " + e.getMessage());
        }
        System.out.println();
    }

    // Deux ressources dans un seul try(...) : fermées dans l'ordre inverse de déclaration
    static void lireEtEcrireEnMemeTemps() {
        System.out.println("=== Lecture + Écriture simultanées – filtre Java >= 14 ===");
        Path src = Path.of("data", "groupe1.csv");
        Path dst = Path.of("data", "groupe1_java_top.csv");
        try (BufferedReader reader = Files.newBufferedReader(src);
             BufferedWriter writer = Files.newBufferedWriter(dst)) {
            // Copie l'en-tête
            writer.write(reader.readLine());
            writer.newLine();
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] cols = ligne.split(";");
                // Garde uniquement les étudiants Java avec note >= 14
                if (cols.length == 5
                        && cols[2].equals("Java")
                        && Double.parseDouble(cols[3]) >= 14.0) {
                    writer.write(ligne);
                    writer.newLine();
                }
            }
            System.out.println("Filtré vers : " + dst);
        } catch (IOException e) {
            System.err.println("Erreur filtre : " + e.getMessage());
        }
        System.out.println();
    }

    // Trois ressources : reader groupe1, reader groupe2, writer fusion – toutes auto-fermées
    static void ouvrirPlusieursRessources() {
        System.out.println("=== Fusion groupe1 + groupe2 → fusion_g1_g2.csv ===");
        Path g1  = Path.of("data", "groupe1.csv");
        Path g2  = Path.of("data", "groupe2.csv");
        Path out = Path.of("data", "fusion_g1_g2.csv");
        try (BufferedReader r1     = Files.newBufferedReader(g1);
             BufferedReader r2     = Files.newBufferedReader(g2);
             BufferedWriter writer = Files.newBufferedWriter(out)) {
            // En-tête une seule fois
            writer.write(r1.readLine());
            writer.newLine();
            r2.readLine(); // ignore l'en-tête du second fichier

            // Copie les deux fichiers à la suite
            String ligne;
            while ((ligne = r1.readLine()) != null) {
                if (!ligne.isBlank()) { writer.write(ligne); writer.newLine(); }
            }
            while ((ligne = r2.readLine()) != null) {
                if (!ligne.isBlank()) { writer.write(ligne); writer.newLine(); }
            }
            System.out.println("Fusion terminée → " + out);
        } catch (IOException e) {
            System.err.println("Erreur fusion : " + e.getMessage());
        }
        System.out.println();
    }

    // Lit les 3 fichiers CSV et écrit dans admis.csv uniquement les notes >= 10
    static void exporterAdmis() {
        System.out.println("=== Admis (note >= 10) tous groupes → admis.csv ===");
        Path[] sources = {
            Path.of("data", "groupe1.csv"),
            Path.of("data", "groupe2.csv"),
            Path.of("data", "groupe3.csv")
        };
        Path out = Path.of("data", "admis.csv");

        try (BufferedWriter writer = Files.newBufferedWriter(out)) {
            writer.write("matricule;nom;module;note;dateSaisie");
            writer.newLine();

            for (Path src : sources) {
                // Chaque reader est déclaré dans son propre try-with-resources
                try (BufferedReader reader = Files.newBufferedReader(src)) {
                    reader.readLine(); // ignore l'en-tête de chaque fichier
                    String ligne;
                    while ((ligne = reader.readLine()) != null) {
                        if (ligne.isBlank()) continue;
                        String[] cols = ligne.split(";");
                        if (cols.length == 5 && Double.parseDouble(cols[3]) >= 10.0) {
                            writer.write(ligne);
                            writer.newLine();
                        }
                    }
                }
            }
            System.out.println("Fichier créé : " + out);
        } catch (IOException e) {
            System.err.println("Erreur export admis : " + e.getMessage());
        }
        System.out.println();
    }

    // Vérifie que admis.csv existe, lit toutes les lignes, trie par note décroissante et réécrit
    static void trierAdmisParNote() {
        System.out.println("=== Tri admis.csv par note décroissante ===");
        Path path = Path.of("data", "admis.csv");

        // Files.exists() avant d'ouvrir pour éviter NoSuchFileException
        if (!Files.exists(path)) {
            System.err.println("admis.csv introuvable – lancez exporterAdmis() d'abord.");
            return;
        }

        List<String> lignes = new ArrayList<>();
        String entete;

        // Lecture : on récupère l'en-tête séparément et on charge les données
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            entete = reader.readLine();
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (!ligne.isBlank()) lignes.add(ligne);
            }
        } catch (IOException e) {
            System.err.println("Erreur lecture : " + e.getMessage());
            return;
        }

        // Tri par note (colonne 3) du plus grand au plus petit
        lignes.sort(Comparator.comparingDouble(
            (String l) -> Double.parseDouble(l.split(";")[3])
        ).reversed());

        // Réécriture : TRUNCATE_EXISTING efface l'ancien contenu avant d'écrire
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(entete);
            writer.newLine();
            for (String ligne : lignes) {
                writer.write(ligne);
                writer.newLine();
            }
            System.out.println("Trié et réécrit : " + lignes.size() + " admis.");
        } catch (IOException e) {
            System.err.println("Erreur écriture : " + e.getMessage());
        }
        System.out.println();
    }
}
