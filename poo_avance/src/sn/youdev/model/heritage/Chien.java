package sn.youdev.model.heritage;

// ============================================================
// CLASSE CONCRÈTE : hérite de Animal, implémente Domestique
//
// extends Animal      → héritage, Chien EST un Animal
// implements Domestique → contrat, Chien respecte l'interface
// ============================================================
public class Chien extends Animal implements Domestique {

    private String race;

    public Chien(String nom, String race) {
        super(nom);  // appel du constructeur de Animal (obligatoire en 1ère ligne)
        this.race = race;
    }

    // ============================================================
    // REDÉFINITION (Override) de parler()
    //
    // @Override : annotation qui dit au compilateur
    //   "je redéfinis une méthode de la classe parente"
    //   → erreur de compilation si la méthode n'existe pas dans Animal
    //
    // Redéfinition = même nom, même paramètres, même type de retour
    // mais comportement différent selon la sous-classe.
    // ============================================================
    @Override
    public void parler() {
        System.out.println(nom + " dit : Ouaf !");
    }

    // ============================================================
    // SURCHARGE (Overload) de parler()
    //
    // Même nom que parler() ci-dessus, mais PARAMÈTRES DIFFÉRENTS
    // → ce n'est PAS la même méthode pour Java
    //
    // Surcharge ≠ Redéfinition :
    //   Surcharge  → même classe, même nom, paramètres différents
    //   Redéfinition → sous-classe, même signature complète
    // ============================================================
    public void parler(String message) {
        System.out.println(nom + " dit : " + message + " (Ouaf !)");
    }

    // ============================================================
    // REDÉFINITION de manger() avec appel à super
    //
    // super.manger() → exécute la version de Animal avant d'ajouter
    // le comportement spécifique au Chien.
    // ============================================================
    @Override
    public void manger() {
        super.manger();  // réutilise le comportement du parent
        System.out.println(nom + " remue la queue en mangeant !");
    }

    // ============================================================
    // IMPLÉMENTATION de l'interface Domestique
    // Obligation : sePresenter() doit être défini ici
    // ============================================================
    @Override
    public void sePresenter() {
        System.out.println("Je m'appelle " + nom + ", je suis un " + race + ".");
    }
}
