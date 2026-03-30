package sn.youdev.app;

import sn.youdev.model.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Forme> formes = new ArrayList<>();

        formes.add(new Rectangle(
                new Point(0.0, 0.0),
                new Point(4.0, 0.0),
                new Point(4.0, 2.0),
                new Point(0.0, 2.0)
        ));

        formes.add(new Carre(
                new Point(0.0, 0.0),
                new Point(2.0, 0.0),
                new Point(2.0, 2.0),
                new Point(0.0, 2.0)
        ));

        formes.add(new Triangle(
                new Point(0.0, 0.0),
                new Point(4.0, 0.0),
                new Point(2.0, 3.0)
        ));

        formes.add(new TriangleRectangle(
                new Point(0.0, 0.0),
                new Point(3.0, 0.0),
                new Point(0.0, 4.0)
        ));

        formes.add(new TriangleIsocele(
                new Point(0.0, 0.0),
                new Point(2.0, 3.0),
                new Point(4.0, 0.0)
        ));

        formes.add(new Cercle(
                new Point(1.0, 1.0),
                3.0
        ));

        System.out.println("=== POLYMORPHISME : LISTE DE FORMES ===");
        for (Forme forme : formes) {
            System.out.println(forme);
        }

        System.out.println("\n=== TRI DES FORMES (Comparable) ===");
        Collections.sort(formes);
        formes.forEach(System.out::println);

        System.out.println("\n=== TEST AVEC SET ===");
        Set<Forme> ensembleFormes = new HashSet<>(formes);
        ensembleFormes.add(new Cercle(new Point(1.0, 1.0), 3.0)); // doublon logique
        System.out.println("Nombre d'elements dans le set : " + ensembleFormes.size());
        ensembleFormes.forEach(System.out::println);

        System.out.println("\n=== TEST AVEC MAP ===");
        Map<Forme, String> descriptions = new HashMap<>();
        for (Forme forme : formes) {
            descriptions.put(forme, forme.getClass().getSimpleName());
        }

        descriptions.forEach((forme, type) ->
                System.out.println(type + " -> surface = " + forme.surface())
        );
    }
}