package sn.youdev.comparator;

import java.util.Comparator;
import sn.youdev.model.Classe;

// Ce comparator compare deux classes par nom.
public class ClasseNomComparator implements Comparator<Classe> {
    @Override
    public int compare(Classe classe1, Classe classe2) {
        return classe1.getNom().compareTo(classe2.getNom());
    }
}
