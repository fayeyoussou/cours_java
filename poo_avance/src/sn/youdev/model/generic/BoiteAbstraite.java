package sn.youdev.model.generic;

// ============================================================
// EXEMPLE 3 : Classe abstraite générique qui implémente l'interface
// On combine : générique + abstract + interface
// Cette classe fournit une base commune, mais laisse les sous-classes
// définir comment afficher les détails (méthode abstraite)
// ============================================================
public abstract class BoiteAbstraite<T> implements Affichable<T> {
    protected T valeur;

    public BoiteAbstraite(T valeur) {
        this.valeur = valeur;
    }

    // Implémentation concrète de l'interface — commune à toutes les sous-classes
    @Override
    public T getValeur() {
        return valeur;
    }

    // Méthode abstraite : chaque sous-classe DOIT définir sa propre façon d'afficher
    @Override
    public abstract void afficherDetails();

    @Override
    public String toString() {
        return "BoiteAbstraite contient : " + valeur;
    }
}
