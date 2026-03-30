package sn.youdev.model.heritage;

// ============================================================
// CLASSE CONCRÈTE : hérite de Animal, implémente Domestique
// Exemple plus simple pour comparer avec Chien
// ============================================================
public class Chat extends Animal implements Domestique {

    public Chat(String nom) {
        super(nom);  // appel du constructeur de Animal
    }

    // REDÉFINITION de parler() → comportement propre au Chat
    @Override
    public void parler() {
        System.out.println(nom + " dit : Miaou !");
    }

    // IMPLÉMENTATION de l'interface Domestique
    @Override
    public void sePresenter() {
        System.out.println("Je m'appelle " + nom + ", je suis un chat.");
    }

    // manger() n'est PAS redéfinie → Chat utilise celle de Animal directement
}
