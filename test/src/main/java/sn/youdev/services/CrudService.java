package sn.youdev.services;

import sn.youdev.exception.EtudiantNotFoundException;
import sn.youdev.model.Etudiant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CrudService {

    private final Path folderPath;
    private static final String SEPARATEUR = ";";

    public CrudService() {
        URL resource = CrudService.class.getClassLoader().getResource("static");
        if (resource == null) throw new IllegalStateException("Dossier 'static' introuvable dans le classpath");
        this.folderPath = Paths.get(URI.create(resource.toString()));
    }

    public CrudService(Path folderPath) {
        this.folderPath = folderPath;
    }

    public List<Etudiant> findAll() throws IOException {
        List<Etudiant> liste = new ArrayList<>();

        try (var stream = Files.list(folderPath)) {
            List<Path> csvFiles = stream
                    .filter(p -> p.toString().endsWith(".csv"))
                    .toList();

            for (Path fichier : csvFiles) {
                try (BufferedReader reader = Files.newBufferedReader(fichier, StandardCharsets.UTF_8)) {
                    String ligne;
                    boolean premiereLigne = true;
                    while ((ligne = reader.readLine()) != null) {
                        ligne = ligne.trim();
                        if (premiereLigne) { premiereLigne = false; continue; }
                        if (!ligne.isEmpty()) {
                            liste.add(deserialiser(ligne));
                        }
                    }
                }
            }
        }
        return liste;
    }

    public Etudiant findByMatricule(String matricule) throws IOException {
        return findAll().stream()
                .filter(e -> e.getMatricule().equalsIgnoreCase(matricule))
                .findFirst()
                .orElseThrow(() -> new EtudiantNotFoundException(matricule));
    }

    public void save(Etudiant etudiant, String fichierCsv) throws IOException {
        Path cible = folderPath.resolve(fichierCsv);
        try (BufferedWriter writer = Files.newBufferedWriter(cible, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(serialiser(etudiant));
            writer.newLine();
        }
    }

    private Etudiant deserialiser(String ligne) {
        String[] parts = ligne.split(SEPARATEUR);
        return new Etudiant(
                parts[0].trim(),
                parts[1].trim(),
                parts[2].trim(),
                Double.parseDouble(parts[3].trim()),
                LocalDate.parse(parts[4].trim())
        );
    }

    private String serialiser(Etudiant e) {
        return e.getMatricule() + SEPARATEUR
                + e.getNom() + SEPARATEUR
                + e.getModule() + SEPARATEUR
                + e.getNote() + SEPARATEUR
                + e.getDateSaisie();
    }
}
