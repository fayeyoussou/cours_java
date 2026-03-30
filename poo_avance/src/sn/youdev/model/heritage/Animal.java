package sn.youdev.model.heritage;

// ============================================================
// CLASSE ABSTRAITE : base commune pour tous les animaux
//
// abstract → on ne peut PAS faire : new Animal()
// Elle sert uniquement de modèle pour les sous-classes.
//
// Peut contenir :
//   - des champs (état)
//   - un constructeur
//   - des méthodes concrètes (avec corps)
//   - des méthodes abstraites (sans corps → obligation pour sous-classes)
// ============================================================
public abstract class Animal {
    protected String nom;  // protected → accessible dans les sous-classes

    public Animal(String nom) {
        this.nom = nom;
    }

    public String getNom() { return nom; }

    // ============================================================
    // MÉTHODE ABSTRAITE : pas de corps ici
    // Chaque sous-classe DOIT fournir sa propre implémentation.
    // C'est ce qui rend le polymorphisme possible.
    // ============================================================
    public abstract void parler();

    // ============================================================
    // MÉTHODE CONCRÈTE : commune à tous les animaux
    // Les sous-classes en héritent directement.
    // Elles peuvent aussi la REDÉFINIR (override) si besoin.
    // ============================================================
    public void manger() {
        System.out.println(nom + " est en train de manger.");
    }

    @Override
    public String toString() {
        return "Animal{nom='" + nom + "'}";
    }
}
