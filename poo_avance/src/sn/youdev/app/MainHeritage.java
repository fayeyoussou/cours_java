package sn.youdev.app;

import sn.youdev.model.heritage.Animal;
import sn.youdev.model.heritage.Chat;
import sn.youdev.model.heritage.Chien;
import sn.youdev.model.heritage.Domestique;

public class MainHeritage {
    public static void main(String[] args) {

        // ============================================================
        // DÉMONSTRATION 1 : Surcharge vs Redéfinition sur Chien
        // ============================================================
        System.out.println("===== DÉMO 1 : Surcharge vs Redéfinition =====");

        Chien rex = new Chien("Rex", "Labrador");

        // REDÉFINITION → parler() défini dans Animal (abstract), implémenté dans Chien
        rex.parler();

        // SURCHARGE → parler(String) est une AUTRE méthode, même nom, paramètres différents
        rex.parler("Bonjour maître");

        // Les deux coexistent dans la même classe → c'est la surcharge
        System.out.println("→ parler()         : redéfinition (override d'Animal)");
        System.out.println("→ parler(String)   : surcharge    (nouvelle signature)");


        // ============================================================
        // DÉMONSTRATION 2 : super → appeler le parent
        // ============================================================
        System.out.println("\n===== DÉMO 2 : super =====");

        // manger() dans Chien appelle super.manger() puis ajoute son comportement
        rex.manger();
        // Chat n'a pas redéfini manger() → utilise directement celle de Animal
        Chat felix = new Chat("Félix");
        felix.manger();


        // ============================================================
        // DÉMONSTRATION 3 : Polymorphisme
        //
        // Un tableau de type Animal peut contenir des Chien et des Chat.
        // Quand on appelle parler(), Java choisit la BONNE version
        // selon le TYPE RÉEL de l'objet (pas le type déclaré).
        // C'est la liaison dynamique (dynamic dispatch).
        // ============================================================
        System.out.println("\n===== DÉMO 3 : Polymorphisme =====");

        // Type déclaré = Animal, mais les objets sont des Chien et Chat
        Animal[] animaux = {
                new Chien("Rex", "Labrador"),
                new Chat("Félix"),
                new Chien("Buddy", "Berger"),
                new Chat("Luna")
        };

        // Même appel animal.parler() → résultat différent selon le vrai type
        for (Animal animal : animaux) {
            animal.parler(); // Java appelle Chien.parler() ou Chat.parler() automatiquement
        }

        // On ne peut PAS faire : new Animal("X") → ERREUR car Animal est abstract
        // Animal a = new Animal("X"); // ERREUR DE COMPILATION


        // ============================================================
        // DÉMONSTRATION 4 : Interface Domestique
        //
        // On peut aussi déclarer avec le type de l'interface.
        // Seules les méthodes de l'interface sont accessibles via ce type.
        // ============================================================
        System.out.println("\n===== DÉMO 4 : Interface Domestique =====");

        Domestique[] domestiques = {
                new Chien("Rex", "Labrador"),
                new Chat("Félix")
        };

        for (Domestique d : domestiques) {
            d.sePresenter();    // méthode de l'interface, implémentée par chacun
            d.saluer();         // méthode default de l'interface
        }

        // Via le type Domestique, on ne peut PAS appeler parler() directement
        // car parler() appartient à Animal, pas à Domestique
        // domestiques[0].parler(); // ERREUR DE COMPILATION


        // ============================================================
        // DÉMONSTRATION 5 : instanceof + cast
        //
        // Quand on a un type générique (Animal, Domestique),
        // on peut vérifier le type réel et accéder aux méthodes spécifiques
        // ============================================================
        System.out.println("\n===== DÉMO 5 : instanceof + cast =====");

        Animal[] mix = {new Chien("Max", "Husky"), new Chat("Mimi")};

        for (Animal animal : mix) {
            if (animal instanceof Chien chien) {
                // cast → on accède à la surcharge parler(String) de Chien
                chien.parler("je suis un chien !");
            } else if (animal instanceof Chat) {
                System.out.println(animal.getNom() + " est un chat, pas un chien.");
            }
        }


        // ============================================================
        // RÉCAPITULATIF
        // ============================================================
        System.out.println("\n===== RÉCAPITULATIF =====");
        System.out.println("Surcharge    : même classe, même nom, paramètres DIFFÉRENTS");
        System.out.println("Redéfinition : sous-classe, même signature, @Override");
        System.out.println("super        : appelle la méthode/constructeur du parent");
        System.out.println("Polymorphisme: type Animal → comportement selon le vrai type");
        System.out.println("Abstract     : force les sous-classes à implémenter");
        System.out.println("Interface    : contrat multiple, pas d'état");
    }
}
