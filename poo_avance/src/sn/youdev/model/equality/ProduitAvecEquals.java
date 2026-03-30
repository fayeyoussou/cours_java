package sn.youdev.model.equality;

import java.util.Objects;

// ============================================================
// CAS 2 : Classe avec SEULEMENT equals() overridé
//
// C'est LE PIÈGE classique des débutants !
// equals() renvoie true pour des objets avec le même code,
// MAIS hashCode() reste celui d'Object (adresse mémoire).
//
// CONTRAT JAVA (à ne jamais violer) :
//   Si a.equals(b) == true  →  a.hashCode() == b.hashCode()
//
// En brisant ce contrat, les objets se "perdent" dans
// les collections basées sur le hachage : HashMap, HashSet,
// Hashtable... car Java cherche dans le MAUVAIS bucket.
// ============================================================
public class ProduitAvecEquals {
    private final String code; // identifiant unique
    private String nom;
    private double prix;

    public ProduitAvecEquals(String code, String nom, double prix) {
        this.code = code;
        this.nom = nom;
        this.prix = prix;
    }

    public String getCode()  { return code; }
    public String getNom()   { return nom; }
    public double getPrix()  { return prix; }

    public void setNom(String nom)   { this.nom = nom; }
    public void setPrix(double prix) { this.prix = prix; }

    // equals() overridé : compare par code (identifiant unique)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                     // même référence → égaux
        if (o == null) return false;                    // null → jamais égal
        if (getClass() != o.getClass()) return false;   // types différents → non égaux
        ProduitAvecEquals autre = (ProduitAvecEquals) o;
        return Objects.equals(code, autre.code);        // seul le code identifie le produit
    }

    // hashCode() NON overridé → utilise l'adresse mémoire
    // RÉSULTAT : deux objets "égaux" selon equals() ont des hashCode différents
    // → COMPORTEMENT IMPRÉVISIBLE dans HashMap et HashSet !
}
