package sn.youdev.java.avance.model.record;

public record Etudiant(String nom, String email) {
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
}
