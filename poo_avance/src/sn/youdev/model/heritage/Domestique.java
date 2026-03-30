package sn.youdev.model.heritage;

// ============================================================
// INTERFACE : définit un CONTRAT que les classes doivent respecter
//
// Une interface n'est PAS une classe → on ne peut pas l'instancier
// Elle déclare des méthodes SANS corps (que les classes implémenteront)
//
// Différence avec classe abstraite :
//   - Interface   → contrat pur, pas d'état, pas de constructeur
//   - Classe abs. → peut avoir des champs, constructeur, méthodes concrètes
//
// Une classe peut implémenter PLUSIEURS interfaces
// mais ne peut hériter que d'UNE seule classe.
// ============================================================
public interface Domestique {

    // Méthode sans corps → les classes qui implémentent DOIVENT la définir
    void sePresenter();

    // Depuis Java 8 : méthode default → corps fourni, optionnel à overrider
    default void saluer() {
        System.out.println("Je suis un animal domestique !");
    }
}
