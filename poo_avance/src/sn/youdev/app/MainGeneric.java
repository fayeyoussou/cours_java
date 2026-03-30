package sn.youdev.app;

import sn.youdev.model.generic.*;

public class MainGeneric {
    public static void main(String[] args) {

        // ============================================================
        // EXEMPLE 1 : Classe générique simple (Boite<T>)
        // La MÊME classe fonctionne avec String, Integer, Double...
        // Sans générique, il faudrait créer une classe par type !
        // ============================================================
        System.out.println("===== EXEMPLE 1 : Boite générique =====");
        Boite<String> boiteStr = new Boite<>("Salut Java");
        Boite<Integer> boiteInt = new Boite<>(42);
        Boite<Double> boiteDbl = new Boite<>(3.14);

        System.out.println(boiteStr);  // Boite contient : Salut Java
        System.out.println(boiteInt);  // Boite contient : 42
        System.out.println(boiteDbl);  // Boite contient : 3.14

        // Sécurité du type : cette ligne ne compilerait PAS
        // boiteStr.setElement(123); // ERREUR : on attend un String, pas un Integer

        // ============================================================
        // EXEMPLE 2 + 3 : Interface + Classe abstraite générique
        // BoiteAbstraite<T> implémente Affichable<T>
        // On utilise le polymorphisme via l'interface
        // ============================================================
        System.out.println("\n===== EXEMPLE 2-3 : Interface + Abstract =====");

        // On déclare avec le type de l'interface (polymorphisme)
        Affichable<Integer> affichableNombre = new BoiteNombre<>(100);
        Affichable<String> affichableTexte = new BoiteTexte("Bonjour le monde");

        // Chaque objet appelle SA propre version de afficherDetails() (override)
        affichableNombre.afficherDetails();
        System.out.println();
        affichableTexte.afficherDetails();

        // ============================================================
        // EXEMPLE 4 : Générique borné (T extends Number)
        // BoiteNombre n'accepte QUE des types numériques
        // On peut utiliser des méthodes de Number (doubleValue, etc.)
        // ============================================================
        System.out.println("\n===== EXEMPLE 4 : Générique borné =====");
        BoiteNombre<Integer> boiteEntier = new BoiteNombre<>(25);
        BoiteNombre<Double> boiteDecimal = new BoiteNombre<>(9.99);

        boiteEntier.afficherDetails();
        System.out.println();
        boiteDecimal.afficherDetails();

        // Cette ligne ne compilerait PAS car String n'est pas un Number :
        // BoiteNombre<String> erreur = new BoiteNombre<>("test"); // ERREUR !

        // ============================================================
        // EXEMPLE 5 : Héritage concret (BoiteTexte fixe T = String)
        // + Polymorphisme avec tableau de BoiteAbstraite
        // Le compilateur appelle le bon afficherDetails() grâce à l'override
        // ============================================================
        System.out.println("\n===== EXEMPLE 5 : Polymorphisme + Override =====");
        BoiteAbstraite<?>[] boites = {
                new BoiteNombre<>(7),
                new BoiteTexte("Generics en Java"),
                new BoiteNombre<>(3.14),
                new BoiteTexte("Interface + Abstract + Override")
        };

        // Boucle polymorphe : chaque boîte appelle SA version de afficherDetails()
        for (BoiteAbstraite<?> b : boites) {
            b.afficherDetails();
            System.out.println("---");
        }
    }
}
