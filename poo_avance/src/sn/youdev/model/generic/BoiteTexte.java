package sn.youdev.model.generic;

// ============================================================
// EXEMPLE 5 : Héritage concret (pas de générique libre)
// On fixe T = String dans BoiteAbstraite<String>
// Cela montre qu'on peut "fermer" le générique dans une sous-classe
// ============================================================
public class BoiteTexte extends BoiteAbstraite<String> {

    public BoiteTexte(String valeur) {
        super(valeur);
    }

    // Méthode spécifique aux textes
    public String enMajuscules() {
        return valeur.toUpperCase();
    }

    public int longueur() {
        return valeur.length();
    }

    // Override obligatoire — on définit l'affichage propre au texte
    @Override
    public void afficherDetails() {
        System.out.println("=== Boîte de Texte ===");
        System.out.println("Valeur      : " + valeur);
        System.out.println("Majuscules  : " + enMajuscules());
        System.out.println("Longueur    : " + longueur());
    }
}
