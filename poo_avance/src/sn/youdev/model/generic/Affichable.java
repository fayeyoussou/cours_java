package sn.youdev.model.generic;

// ============================================================
// EXEMPLE 2 : Interface générique
// On définit un contrat générique : tout ce qui est "Affichable"
// doit pouvoir afficher ses détails et retourner sa valeur typée
// ============================================================
public interface Affichable<T> {

    // Retourne la valeur contenue, avec le BON type grâce au générique
    T getValeur();

    // Affiche les détails de l'objet
    void afficherDetails();
}
