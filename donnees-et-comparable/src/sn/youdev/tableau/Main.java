package sn.youdev.tableau;

import sn.youdev.model.Classe;
import sn.youdev.model.Etudiant;
import sn.youdev.model.Note;
import sn.youdev.model.Point;

public class Main {
    public static void main(String[] args) {
        Note[] notes = new Note[3];
        notes[0] = new Note(1, "Java", 15.5);
        notes[1] = new Note(2, "Python", 12.0);

        Etudiant[] etudiants = new Etudiant[3];
        etudiants[0] = new Etudiant("E001", "Awa", 14.5);
        etudiants[1] = new Etudiant("E002", "Moussa", 13.0);

        Point[] points = new Point[3];
        points[0] = new Point(2, 4);
        points[1] = new Point(3, 1);

        Classe[] classes = new Classe[3];
        classes[0] = new Classe("C001", "L1 GL", "Licence 1", 45);
        classes[1] = new Classe("C002", "L2 GL", "Licence 2", 38);

        // Un tableau a une taille fixe.
        System.out.println("Taille du tableau notes = " + notes.length);
        System.out.println("Taille du tableau etudiants = " + etudiants.length);

        // L'acces se fait par index.
        System.out.println("Premier etudiant = " + etudiants[0]);
        System.out.println("Deuxieme point = " + points[1]);

        // Une case non remplie contient null pour un tableau d'objets.
        System.out.println("Troisieme note = " + notes[2]);

        // On peut modifier une case si l'index existe.
        notes[2] = new Note(3, "SVT", 16.0);
        classes[2] = new Classe("C003", "L3 GL", "Licence 3", 30);
        System.out.println("Troisieme note apres ajout = " + notes[2]);
        System.out.println("Troisieme classe apres ajout = " + classes[2]);

        // On peut aussi remplacer une ancienne valeur.
        etudiants[1] = new Etudiant("E002", "Moussa", 15.0);
        System.out.println("Etudiant remplace a l'index 1 = " + etudiants[1]);

        // Il faut verifier la limite avant l'acces.
        int index = 5;
        if (index >= 0 && index < notes.length) {
            System.out.println("Note a l'index 5 = " + notes[index]);
        } else {
            System.out.println("Index 5 impossible : le tableau notes va de 0 a " + (notes.length - 1));
        }

        // Parcours avec for classique.
        System.out.println("Parcours des notes avec for");
        for (int i = 0; i < notes.length; i++) {
            System.out.println("notes[" + i + "] = " + notes[i]);
        }

        // Parcours avec for-each.
        System.out.println("Parcours des etudiants avec for-each");
        for (Etudiant etudiant : etudiants) {
            System.out.println(etudiant);
        }

        // On peut compter les cases remplies.
        int nombreDeNotesRemplies = 0;
        for (int i = 0; i < notes.length; i++) {
            if (notes[i] != null) {
                nombreDeNotesRemplies++;
            }
        }
        System.out.println("Nombre de notes remplies = " + nombreDeNotesRemplies);

        // Recherche simple dans un tableau.
        String matriculeRecherche = "E002";
        boolean trouve = false;
        for (int i = 0; i < etudiants.length; i++) {
            if (etudiants[i] != null && etudiants[i].getMatricule().equals(matriculeRecherche)) {
                System.out.println("Etudiant trouve a l'index " + i + " = " + etudiants[i]);
                trouve = true;
                break;
            }
        }
        if (!trouve) {
            System.out.println("Etudiant non trouve");
        }

        // Meme idee avec les autres modeles.
        if (1 < etudiants.length) {
            System.out.println("Etudiant a l'index 1 = " + etudiants[1]);
        }

        if (0 < points.length) {
            System.out.println("Point a l'index 0 = " + points[0]);
        }

        if (2 < classes.length) {
            System.out.println("Classe a l'index 2 = " + classes[2]);
        }

        // Dernier index valide.
        System.out.println("Dernier index valide pour classes = " + (classes.length - 1));
    }
}
