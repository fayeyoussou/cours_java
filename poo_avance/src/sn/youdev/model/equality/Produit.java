package sn.youdev.model.equality;

// ============================================================
// CAS 1 : Classe SANS override de equals() ni hashCode()
//
// Comportement par défaut hérité de Object :
//   - equals()   → compare les RÉFÉRENCES (adresses mémoire)
//   - hashCode() → basé sur l'adresse mémoire de l'objet
//
// PROBLÈME : deux objets avec les mêmes données sont considérés
// comme DIFFÉRENTS par Java, ce qui est rarement ce qu'on veut.
// ============================================================
public class Produit {
    private String code; // identifiant unique
    private String nom;
    private double prix;

    public Produit(String code, String nom, double prix) {
        this.code = code;
        this.nom = nom;
        this.prix = prix;
    }

    public String getCode()  { return code; }
    public String getNom()   { return nom; }
    public double getPrix()  { return prix; }

    public void setNom(String nom)   { this.nom = nom; }
    public void setPrix(double prix) { this.prix = prix; }

    // Aucun equals() → Java utilise Object.equals() = comparaison de références
    // Aucun hashCode() → Java utilise l'adresse mémoire comme code de hachage
}
