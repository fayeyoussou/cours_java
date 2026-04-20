package sn.youdev.java.avance.app;

import sn.youdev.java.avance.model.record.Etudiant;
import sn.youdev.java.avance.model.record.Note;
import sn.youdev.java.avance.model.record.Point;
import sn.youdev.java.avance.model.record.Rectangle;
import sn.youdev.java.avance.model.record.Temperature;

public class MainRecord {
    public static void main(String[] args) {
        Point point1 = new Point(2, 4);
        Point point2 = new Point(2, 4);
        Note note = new Note(15.5);
        Etudiant etudiant = new Etudiant(" Awa Ndiaye ", "AWA@ecole.sn", new Etudiant.NoteInterne(12));
        Rectangle carre = new Rectangle(5);
        Temperature temperature = Temperature.fromFahrenheit(77);

        // Les accesseurs portent le nom des composants.
        IO.println("x : " + point1.x());
        IO.println("y : " + point1.y());

        // toString est généré automatiquement.
        IO.println("Point : " + point1);

        // equals compare les valeurs du record.
        IO.println("Points égaux : " + point1.equals(point2));

        // hashCode est aussi généré automatiquement.
        IO.println("Hash : " + point1.hashCode());

        // Un record peut contenir une validation.
        IO.println("Note valide : " + note.valeur());

        // Un record peut normaliser les données dans son constructeur.
        IO.println("Étudiant : " + etudiant);

        // Un record peut avoir un constructeur secondaire.
        IO.println("Carré : " + carre);

        // Un record peut avoir ses propres méthodes.
        IO.println("Aire : " + carre.aire());

        // Un record peut avoir une méthode statique de fabrique.
        IO.println("Température : " + temperature.celsius() + " C");
        IO.println("En Fahrenheit : " + temperature.toFahrenheit() + " F");

        // Une mauvaise valeur lève l'exception du constructeur compact.
        try {
            new Note(22);
        } catch (IllegalArgumentException e) {
            IO.println("Erreur : " + e.getMessage());
        }
    }
}
