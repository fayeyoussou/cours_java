package sn.youdev.model.equality;

import java.util.Objects;

// ============================================================
// CAS 3 : Classe avec equals() ET hashCode() correctement overridés
//
// RÈGLES D'UN BON equals() :
//   1. Réflexivité  : x.equals(x) == true
//   2. Symétrie     : x.equals(y) == y.equals(x)
//   3. Transitivité : si x.equals(y) et y.equals(z) → x.equals(z)
//   4. Cohérence    : résultat stable si les champs ne changent pas
//   5. Null-safe    : x.equals(null) == false (jamais d'exception)
//
// RÈGLES D'UN BON hashCode() :
//   1. Si a.equals(b) → a.hashCode() == b.hashCode()  (obligatoire)
//   2. Si !a.equals(b) → les hashCode PEUVENT être différents
//      (collisions autorisées, mais à minimiser pour les perfs)
//   3. Stable : même résultat si les champs utilisés ne changent pas
//
// BONNE PRATIQUE : baser equals/hashCode sur un champ immuable (final)
// qui identifie l'objet de façon unique, comme un ID.
// → nom et prix peuvent changer sans affecter le comportement
//   dans les HashSet/HashMap.
// ============================================================
public class ProduitCorrect {
    private final String code; // identifiant unique et immuable (final)
    private String nom;        // peut changer → pas dans equals/hashCode
    private double prix;       // peut changer → pas dans equals/hashCode

    public ProduitCorrect(String code, String nom, double prix) {
        this.code = code;
        this.nom = nom;
        this.prix = prix;
    }

    public String getCode()  { return code; }
    public String getNom()   { return nom; }
    public double getPrix()  { return prix; }

    public void setNom(String nom)   { this.nom = nom; }
    public void setPrix(double prix) { this.prix = prix; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                   // optimisation : même objet
        if (o == null) return false;                  // null-safe
        if (getClass() != o.getClass()) return false; // vérification du type exact
        ProduitCorrect autre = (ProduitCorrect) o;
        return Objects.equals(code, autre.code);      // seul le code identifie le produit
    }

    @Override
    public int hashCode() {
        // Basé sur code uniquement → stable même si nom ou prix change
        // IMPORTANT : utiliser les MÊMES champs que dans equals()
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Produit{code='" + code + "', nom='" + nom + "', prix=" + prix + "}";
    }
}
