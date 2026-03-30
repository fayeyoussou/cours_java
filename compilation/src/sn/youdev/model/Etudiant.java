package sn.youdev.model;

public class Etudiant extends Personne {
    private static int compteur = 0;

    private final String matricule;
    private String email;

    public Etudiant() {
        this("ND-000", "Inconnu", "Inconnu");
    }

    public Etudiant(String matricule) {
        this(matricule, "Inconnu", "Inconnu");
    }

    public Etudiant(String matricule, String nom, String prenom) {
        super(nom, prenom);
        this.matricule = validerMatricule(matricule);
        this.email = "";
        compteur++;
    }

    public Etudiant(String matricule, String nom, String prenom, String email) {
        this(matricule, nom, prenom);
        setEmail(email);
    }

    public String getMatricule() {
        return matricule;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            this.email = "";
            return;
        }
        String valeur = email.trim();
        if (!valeur.contains("@") || !valeur.contains(".")) {
            throw new IllegalArgumentException("Email invalide");
        }
        this.email = valeur;
    }

    public void afficher() {
        String emailAffiche = email.isBlank() ? "email non renseigne" : email;
        System.out.println(matricule + " - " + getNomComplet() + " - " + emailAffiche);
    }

    public static int getCompteur() {
        return compteur;
    }

    private static String validerMatricule(String matricule) {
        if (matricule == null || matricule.isBlank()) {
            throw new IllegalArgumentException("Matricule invalide");
        }
        return matricule.trim();
    }
}
