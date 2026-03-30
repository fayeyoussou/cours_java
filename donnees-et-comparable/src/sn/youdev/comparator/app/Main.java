package sn.youdev.comparator.app;

import java.util.Comparator;
import sn.youdev.comparator.ClasseNomComparator;
import sn.youdev.comparator.EtudiantMoyenneComparator;
import sn.youdev.comparator.EtudiantNomComparator;
import sn.youdev.model.Classe;
import sn.youdev.model.Etudiant;
import sn.youdev.model.Note;
import sn.youdev.model.Point;

public class Main {
    public static void main(String[] args) {
        // Comparable : ordre naturel de Note
        Note note1 = new Note(1, "Math", 15.5);
        Note note2 = new Note(2, "Physique", 12.0);
        System.out.println("note1.compareTo(note2) = " + note1.compareTo(note2));
        System.out.println("note1.equals(note2) = " + note1.equals(note2));

        // Comparator dans Main : comparaison par id
        Comparator<Note> noteIdComparator = new Comparator<Note>() {
            @Override
            public int compare(Note n1, Note n2) {
                return Integer.compare(n1.getId(), n2.getId());
            }
        };
        System.out.println("Comparaison note par id = " + noteIdComparator.compare(note1, note2));

        // Comparable : ordre naturel de Point
        Point point1 = new Point(2, 4);
        Point point2 = new Point(3, 1);
        System.out.println("point1.compareTo(point2) = " + point1.compareTo(point2));
        System.out.println("point1.equals(point2) = " + point1.equals(point2));

        // Comparator dans Main : comparaison par y
        Comparator<Point> pointYComparator = new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return Integer.compare(p1.getY(), p2.getY());
            }
        };
        System.out.println("Comparaison point par y = " + pointYComparator.compare(point1, point2));

        // Comparable : ordre naturel de Etudiant
        Etudiant etudiant1 = new Etudiant("E001", "Awa", 14.5);
        Etudiant etudiant2 = new Etudiant("E002", "Moussa", 13.0);
        System.out.println("etudiant1.compareTo(etudiant2) = " + etudiant1.compareTo(etudiant2));
        System.out.println("etudiant1.equals(etudiant2) = " + etudiant1.equals(etudiant2));

        // Comparator : autre comparaison sur Etudiant
        EtudiantNomComparator etudiantNomComparator = new EtudiantNomComparator();
        EtudiantMoyenneComparator etudiantMoyenneComparator = new EtudiantMoyenneComparator();
        System.out.println("Comparaison par nom = " + etudiantNomComparator.compare(etudiant1, etudiant2));
        System.out.println("Comparaison par moyenne = " + etudiantMoyenneComparator.compare(etudiant1, etudiant2));

        // Comparator dans Main : comparaison par longueur du nom
        Comparator<Etudiant> etudiantLongueurNomComparator = new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant e1, Etudiant e2) {
                return Integer.compare(e1.getNom().length(), e2.getNom().length());
            }
        };
        System.out.println("Comparaison etudiant par longueur du nom = "
                + etudiantLongueurNomComparator.compare(etudiant1, etudiant2));

        // Comparable : ordre naturel de Classe
        Classe classe1 = new Classe("C001", "L1 GL", "Licence 1", 45);
        Classe classe2 = new Classe("C002", "L2 GL", "Licence 2", 38);
        System.out.println("classe1.compareTo(classe2) = " + classe1.compareTo(classe2));
        System.out.println("classe1.equals(classe2) = " + classe1.equals(classe2));

        // Comparator : autre comparaison sur Classe
        ClasseNomComparator classeNomComparator = new ClasseNomComparator();
        System.out.println("Comparaison classe par nom = " + classeNomComparator.compare(classe1, classe2));

        // Comparator dans Main : comparaison par code
        Comparator<Classe> classeCodeComparator = new Comparator<Classe>() {
            @Override
            public int compare(Classe c1, Classe c2) {
                return c1.getCode().compareTo(c2.getCode());
            }
        };
        System.out.println("Comparaison classe par code = " + classeCodeComparator.compare(classe1, classe2));
    }
}
