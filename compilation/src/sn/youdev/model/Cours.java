package sn.youdev.model;

public class Cours {
    private static int totalCours = 0;

    private String code;
    private String titre;
    private int dureeHeures;

    public Cours(String code, String titre, int dureeHeures) {
        setCode(code);
        setTitre(titre);
        setDureeHeures(dureeHeures);
        totalCours++;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Code cours invalide");
        }
        this.code = code.trim().toUpperCase();
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        if (titre == null || titre.isBlank()) {
            throw new IllegalArgumentException("Titre cours invalide");
        }
        this.titre = titre.trim();
    }

    public int getDureeHeures() {
        return dureeHeures;
    }

    public void setDureeHeures(int dureeHeures) {
        if (dureeHeures <= 0) {
            throw new IllegalArgumentException("Duree invalide");
        }
        this.dureeHeures = dureeHeures;
    }

    public static int getTotalCours() {
        return totalCours;
    }
}
