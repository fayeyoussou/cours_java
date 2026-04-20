package sn.youdev.model;

import sn.youdev.exception.NoteInvalideException;
import java.time.LocalDate;

// Note d'un étudiant pour un module – valide uniquement entre 0 et 20
public class Note {
    private final Etudiant etudiant;
    private final Module module;
    private final double valeur;
    private final LocalDate dateSaisie;

    public Note(Etudiant etudiant, Module module, double valeur, LocalDate dateSaisie)
            throws NoteInvalideException {
        if (valeur < 0 || valeur > 20) throw new NoteInvalideException(valeur);
        this.etudiant = etudiant;
        this.module = module;
        this.valeur = valeur;
        this.dateSaisie = dateSaisie;
    }

    public Etudiant getEtudiant() { return etudiant; }
    public Module getModule() { return module; }
    public double getValeur() { return valeur; }
    public LocalDate getDateSaisie() { return dateSaisie; }

    @Override
    public String toString() {
        return etudiant.getNom() + " | " + module + " | " + valeur + " | " + dateSaisie;
    }
}
