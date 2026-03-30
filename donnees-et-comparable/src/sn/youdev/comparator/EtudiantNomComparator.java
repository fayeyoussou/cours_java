package sn.youdev.comparator;

import java.util.Comparator;
import sn.youdev.model.Etudiant;

// Ce comparator compare deux etudiants par nom.
public class EtudiantNomComparator implements Comparator<Etudiant> {
    @Override
    public int compare(Etudiant etudiant1, Etudiant etudiant2) {
        return etudiant1.getNom().compareTo(etudiant2.getNom());
    }
}
