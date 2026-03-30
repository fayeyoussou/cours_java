package sn.youdev.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import sn.youdev.comparator.EtudiantNomComparator;
import sn.youdev.model.Etudiant;
import sn.youdev.model.Note;
import sn.youdev.model.Point;

public class Main {
    public static void main(String[] args) {
        // Un Set ne garde pas les doublons.
        Set<Note> notes = new HashSet<Note>();
        notes.add(new Note(1, "Java", 15.5));
        notes.add(new Note(2, "Python", 12.0));
        notes.add(new Note(2, "Python copie", 18.0));

        System.out.println("Taille du HashSet notes = " + notes.size());
        System.out.println("HashSet notes = " + notes);

        // contains verifie si un element existe.
        System.out.println("notes contient id 1 = " + notes.contains(new Note(1, "Java", 15.5)));

        // remove supprime un element.
        notes.remove(new Note(1, "Java", 15.5));
        System.out.println("HashSet notes apres remove = " + notes);

        // forEach parcourt le set.
        System.out.println("Parcours du HashSet avec forEach");
        notes.forEach(note -> System.out.println(note));

        // iterator permet un parcours manuel.
        System.out.println("Parcours du HashSet avec iterator");
        Iterator<Note> iteratorNotes = notes.iterator();
        while (iteratorNotes.hasNext()) {
            System.out.println(iteratorNotes.next());
        }

        // LinkedHashSet garde l'ordre d'insertion.
        Set<Point> points = new LinkedHashSet<Point>();
        points.add(new Point(3, 1));
        points.add(new Point(1, 5));
        points.add(new Point(2, 4));
        points.add(new Point(1, 5));

        System.out.println("LinkedHashSet points = " + points);

        // Un Set n'a pas d'index comme un tableau ou une liste.
        System.out.println("Dans un Set, on ne fait pas get(0)");

        // TreeSet trie selon Comparable.
        Set<Note> notesTriees = new TreeSet<Note>();
        notesTriees.add(new Note(5, "Reseau", 10.0));
        notesTriees.add(new Note(6, "Java", 15.0));
        notesTriees.add(new Note(7, "UML", 13.0));

        System.out.println("TreeSet notes triees avec Comparable");
        notesTriees.forEach(note -> System.out.println(note));

        // TreeSet peut aussi trier avec Comparator.
        Set<Etudiant> etudiantsTriesParNom = new TreeSet<Etudiant>(new EtudiantNomComparator());
        etudiantsTriesParNom.add(new Etudiant("E003", "Fatou", 11.0));
        etudiantsTriesParNom.add(new Etudiant("E001", "Awa", 14.5));
        etudiantsTriesParNom.add(new Etudiant("E002", "Moussa", 13.0));

        System.out.println("TreeSet etudiants tries avec Comparator par nom");
        etudiantsTriesParNom.forEach(etudiant -> System.out.println(etudiant));

        // removeIf marche aussi sur un Set.
        etudiantsTriesParNom.removeIf(etudiant -> etudiant.getMoyenne() < 12);
        System.out.println("Etudiants apres removeIf");
        etudiantsTriesParNom.forEach(etudiant -> System.out.println(etudiant));

        // En pratique simple :
        System.out.println("HashSet : pas de doublon, ordre non garanti");
        System.out.println("LinkedHashSet : pas de doublon, garde l'ordre d'insertion");
        System.out.println("TreeSet : pas de doublon, elements tries");
    }
}
