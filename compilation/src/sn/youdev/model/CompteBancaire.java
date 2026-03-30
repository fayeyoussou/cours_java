package sn.youdev.model;

public class CompteBancaire {
    private static int nombreComptes = 0;

    private final String numero;
    private String proprietaire;
    private double solde;

    public CompteBancaire(String numero, String proprietaire) {
        this(numero, proprietaire, 0);
    }

    public CompteBancaire(String numero, String proprietaire, double soldeInitial) {
        this.numero = validerNumero(numero);
        setProprietaire(proprietaire);
        if (soldeInitial < 0) {
            throw new IllegalArgumentException("Solde initial invalide");
        }
        this.solde = soldeInitial;
        nombreComptes++;
    }

    public String getNumero() {
        return numero;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        if (proprietaire == null || proprietaire.isBlank()) {
            throw new IllegalArgumentException("Proprietaire invalide");
        }
        this.proprietaire = proprietaire.trim();
    }

    public double getSolde() {
        return solde;
    }

    public void deposer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Montant depot invalide");
        }
        solde += montant;
    }

    public void retirer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Montant retrait invalide");
        }
        if (montant > solde) {
            throw new IllegalArgumentException("Solde insuffisant");
        }
        solde -= montant;
    }

    public static int getNombreComptes() {
        return nombreComptes;
    }

    private static String validerNumero(String numero) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Numero invalide");
        }
        return numero.trim();
    }
}
