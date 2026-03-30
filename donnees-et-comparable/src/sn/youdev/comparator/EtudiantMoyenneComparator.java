package sn.youdev.comparator;

import java.util.Comparator;
import sn.youdev.model.Etudiant;

// Ce comparator compare deux etudiants par moyenne.
public class EtudiantMoyenneComparator implements Comparator<Etudiant> {
    @Override
    public int compare(Etudiant etudiant1, Etudiant etudiant2) {
        return Double.compare(etudiant1.getMoyenne(), etudiant2.getMoyenne());
    }
}
