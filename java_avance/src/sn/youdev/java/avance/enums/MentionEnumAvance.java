package sn.youdev.java.avance.enums;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.Optional;

public enum MentionEnumAvance {
    NON_ADMIS("Non admis", 0),
    PASSABLE("Passable", 10),
    ASSEZ_BIEN("Assez bien", 12),
    BIEN("Bien", 14),
    TRES_BIEN("Très bien", 16);

    private final String valeur;
    private final double noteMin;

    MentionEnumAvance(String valeur, double noteMin) {
        this.valeur = valeur;
        this.noteMin = noteMin;
    }

    public String getValeur() {
        return valeur;
    }

    public double getNoteMin() {
        return noteMin;
    }

    public boolean isAdmis() {
        return this != NON_ADMIS;
    }

    public static Optional<MentionEnumAvance> of(String valeur) {
        if (valeur == null || valeur.isBlank()) {
            return Optional.empty();
        }

        String valeurNormalisee = normaliser(valeur);

        return Arrays.stream(values())
                .filter(mention -> normaliser(mention.name()).equals(valeurNormalisee)
                        || normaliser(mention.valeur).equals(valeurNormalisee))
                .findFirst();
    }

    public static Optional<MentionEnumAvance> fromNote(double note) {
        if (note < 0 || note > 20) {
            return Optional.empty();
        }
        if (note >= TRES_BIEN.noteMin) {
            return Optional.of(TRES_BIEN);
        }
        if (note >= BIEN.noteMin) {
            return Optional.of(BIEN);
        }
        if (note >= ASSEZ_BIEN.noteMin) {
            return Optional.of(ASSEZ_BIEN);
        }
        if (note >= PASSABLE.noteMin) {
            return Optional.of(PASSABLE);
        }
        return Optional.of(NON_ADMIS);
    }

    @Override
    public String toString() {
        return valeur;
    }

    private static String normaliser(String texte) {
        // Décompose les caractères accentués pour pouvoir retirer les accents.
        return Normalizer.normalize(texte, Normalizer.Form.NFD)
                // Supprime les accents et autres marques diacritiques.
                .replaceAll("\\p{M}+", "")
                // Retire les espaces au début et à la fin.
                .trim()
                // Remplace les espaces internes par des underscores.
                .replace(' ', '_')
                // Met le texte en majuscules pour comparer plus facilement.
                .toUpperCase();
    }
}
