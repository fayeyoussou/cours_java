package sn.youdev.java.avance.model.record;

public record Etudiant(String nom, String email,NoteInterne note) {
    // Le constructeur compact peut valider et normaliser les données.
    public Etudiant {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Nom vide");
        }
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Email invalide");
        }
        nom = nom.trim();
        email = email.trim().toLowerCase();
    }
    public record NoteInterne(double valeur) {
        // Constructeur compact (pas de parenthèses)
        public NoteInterne {
            if (valeur < 0 || valeur > 20) {
                throw new IllegalArgumentException("Note invalide : " + valeur);
            }
            // Normalisation possible : valeur = Math.round(valeur);
        }
    }
}
