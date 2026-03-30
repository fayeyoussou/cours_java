package sn.youdev.liste;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import sn.youdev.comparator.ClasseNomComparator;
import sn.youdev.comparator.EtudiantMoyenneComparator;
import sn.youdev.comparator.EtudiantNomComparator;
import sn.youdev.model.Classe;
import sn.youdev.model.Etudiant;
import sn.youdev.model.Note;
import sn.youdev.model.Point;

public class Main {
    public static void main(String[] args) {
        // ArrayList : pratique pour acceder vite par index.
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1, "Java", 15.5));
        notes.add(new Note(2, "Python", 12.0));
        notes.add(new Note(3, "UML", 16.0));

        System.out.println("Taille de notes = " + notes.size());
        System.out.println("Premier element = " + notes.get(0));

        // On peut parcourir une liste avec for.
        System.out.println("Parcours des notes avec for");
        for (int i = 0; i < notes.size(); i++) {
            System.out.println("notes[" + i + "] = " + notes.get(i));
        }

        // On peut parcourir aussi avec for-each.
        System.out.println("Parcours des notes avec for-each");
        for (Note note : notes) {
            System.out.println(note);
        }

        // Une liste peut grandir avec add.
        notes.add(new Note(4, "Reseau", 10.5));
        System.out.println("Taille apres add = " + notes.size());

        // On peut modifier avec set.
        notes.set(1, new Note(2, "Python", 14.0));
        System.out.println("Element modifie a l'index 1 = " + notes.get(1));

        // On peut supprimer avec remove.
        notes.remove(3);
        System.out.println("Taille apres remove = " + notes.size());

        // contains verifie si un element existe.
        System.out.println("notes contient Python 14.0 = "
                + notes.contains(new Note(2, "Python", 14.0)));

        // Tri avec Comparable : ordre naturel de Note par valeur.
        Collections.sort(notes);
        System.out.println("Notes triees avec Comparable");
        for (Note note : notes) {
            System.out.println(note);
        }

        // forEach parcourt la liste simplement.
        System.out.println("Parcours des notes avec forEach");
        notes.forEach(note -> System.out.println(note));

        List<Etudiant> etudiants = new ArrayList<Etudiant>();
        etudiants.add(new Etudiant("E003", "Fatou", 11.0));
        etudiants.add(new Etudiant("E001", "Awa", 14.5));
        etudiants.add(new Etudiant("E002", "Moussa", 13.0));

        // Tri avec Comparable : ordre naturel de Etudiant par matricule.
        Collections.sort(etudiants);
        System.out.println("Etudiants tries avec Comparable");
        for (Etudiant etudiant : etudiants) {
            System.out.println(etudiant);
        }

        // Tri avec Comparator : ordre par nom.
        Collections.sort(etudiants, new EtudiantNomComparator());
        System.out.println("Etudiants tries avec Comparator par nom");
        for (Etudiant etudiant : etudiants) {
            System.out.println(etudiant);
        }

        // Tri avec Comparator : ordre par moyenne.
        etudiants.sort(new EtudiantMoyenneComparator());
        System.out.println("Etudiants tries avec Comparator par moyenne");
        for (Etudiant etudiant : etudiants) {
            System.out.println(etudiant);
        }

        // iterator permet un parcours manuel.
        System.out.println("Parcours des etudiants avec iterator");
        Iterator<Etudiant> iterator = etudiants.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // removeIf supprime selon une condition.
        etudiants.removeIf(etudiant -> etudiant.getMoyenne() < 12);
        System.out.println("Etudiants apres removeIf");
        etudiants.forEach(etudiant -> System.out.println(etudiant));

        List<Classe> classes = new ArrayList<Classe>();
        classes.add(new Classe("C003", "L3 GL", "Licence 3", 30));
        classes.add(new Classe("C001", "L1 GL", "Licence 1", 45));
        classes.add(new Classe("C002", "L2 GL", "Licence 2", 38));

        // Comparable : ordre naturel de Classe par effectif.
        Collections.sort(classes);
        System.out.println("Classes triees avec Comparable");
        for (Classe classe : classes) {
            System.out.println(classe);
        }

        // Comparator : ordre par nom.
        classes.sort(new ClasseNomComparator());
        System.out.println("Classes triees avec Comparator par nom");
        for (Classe classe : classes) {
            System.out.println(classe);
        }

        List<Point> points = new ArrayList<Point>();
        points.add(new Point(3, 1));
        points.add(new Point(1, 5));
        points.add(new Point(2, 4));

        // Comparable : ordre naturel de Point par x puis y.
        Collections.sort(points);
        System.out.println("Points tries avec Comparable");
        for (Point point : points) {
            System.out.println(point);
        }

        // Comparator direct dans Main : ordre par y.
        points.sort((point1, point2) -> Integer.compare(point1.getY(), point2.getY()));
        System.out.println("Points tries avec Comparator par y");
        for (Point point : points) {
            System.out.println(point);
        }

        // ArrayList et LinkedList gardent le meme type List.
        List<String> arrayList = new ArrayList<String>();
        LinkedList<String> linkedList = new LinkedList<String>();

        arrayList.add("Java");
        arrayList.add("Python");
        arrayList.add("C");

        linkedList.add("Java");
        linkedList.add("Python");
        linkedList.add("C");

        System.out.println("ArrayList = " + arrayList);
        System.out.println("LinkedList = " + linkedList);

        // ArrayList est souvent meilleur pour l'acces par index.
        System.out.println("ArrayList index 1 = " + arrayList.get(1));

        // LinkedList est souvent choisi quand on ajoute ou retire beaucoup.
        linkedList.addFirst("UML");
        linkedList.remove(2);
        System.out.println("LinkedList apres ajout et suppression = " + linkedList);
        System.out.println("Premier element de LinkedList = " + linkedList.getFirst());

        // contains marche aussi avec les chaines.
        System.out.println("LinkedList contient Java = " + linkedList.contains("Java"));

        // En pratique simple :
        System.out.println("ArrayList : bon pour lire souvent avec get(index)");
        System.out.println("LinkedList : bon pour beaucoup d'ajouts ou retraits");
    }
}
