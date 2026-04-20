package sn.youdev.file;

import sn.youdev.exception.EtudiantInexistantException;
import sn.youdev.exception.NoteInvalideException;
import sn.youdev.exception.ServiceException;
import sn.youdev.model.Bilan;
import sn.youdev.model.Etudiant;
import sn.youdev.model.Module;
import sn.youdev.model.Note;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

// Service : lecture CSV, parsing ligne par ligne et gestion complète des exceptions
public class EtudiantService {

    private final List<Etudiant> etudiants = new ArrayList<>();
    private final List<Note> notes = new ArrayList<>();

    // Importe toutes les notes d'un fichier CSV et retourne un bilan succès/échecs
    public Bilan importer(Path path) throws ServiceException {
        try {
            List<String> lignes = Files.readAllLines(path);
            List<String> logs = new ArrayList<>();
            int ok = 0;

            for (int i = 1; i < lignes.size(); i++) {
                String ligne = lignes.get(i);
                if (ligne.isBlank()) continue;
                try {
                    Note note = parseLigne(ligne);
                    notes.add(note);
                    etudiants.add(note.getEtudiant());
                    ok++;
                } catch (NoteInvalideException | DateTimeParseException | NumberFormatException e) {
                    logs.add("Ligne " + (i + 1) + " : " + e.getMessage());
                }
            }
            return new Bilan(ok, logs.size(), logs);
        } catch (IOException e) {
            // On wrappe l'IOException en ServiceException pour ne pas exposer les détails I/O
            throw new ServiceException("Impossible de lire le fichier : " + path, e);
        }
    }

    // Parse une ligne CSV au format : matricule;nom;module;note;dateSaisie
    private Note parseLigne(String ligne) throws NoteInvalideException {
        String[] cols = ligne.split(";");
        if (cols.length != 5) throw new NoteInvalideException("Format incorrect : 5 colonnes requises");
        String matricule = cols[0].trim();
        String nom       = cols[1].trim();
        String module    = cols[2].trim();
        double valeur    = Double.parseDouble(cols[3].trim().replace(',', '.'));
        LocalDate date   = LocalDate.parse(cols[4].trim());
        return new Note(new Etudiant(matricule, nom), new Module(module), valeur, date);
    }

    // Recherche un étudiant par matricule – lève une exception au lieu de retourner null
    public Etudiant trouverParMatricule(String matricule) throws EtudiantInexistantException {
        for (Etudiant e : etudiants) {
            if (e.getMatricule().equals(matricule)) return e;
        }
        throw new EtudiantInexistantException("Aucun étudiant trouvé avec le matricule : " + matricule);
    }

    public List<Note> getNotes()         { return notes; }
    public List<Etudiant> getEtudiants() { return etudiants; }
}
