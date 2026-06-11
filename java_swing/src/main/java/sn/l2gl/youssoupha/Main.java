package sn.l2gl.youssoupha;

import sn.l2gl.youssoupha.exception.EtudiantNonTrouveException;
import sn.l2gl.youssoupha.hibernate.view.AppHibernate;
import sn.l2gl.youssoupha.hibernate_simple.dao.EtudiantDao;
import sn.l2gl.youssoupha.hibernate_simple.model.Etudiant;
import sn.l2gl.youssoupha.hibernate_simple.view.AppEtudiant;
import sn.l2gl.youssoupha.hibernate_simple.view.EtudiantView;
import sn.l2gl.youssoupha.ui.InterfaceFinaleEtudiant;

import java.util.Optional;

public class Main {
    public static void main(String[] args)  {
        InterfaceFinaleEtudiant.afficher();

    }

    private static void chercherEtudiant() throws EtudiantNonTrouveException {
        EtudiantDao etudiantDao = new EtudiantDao();
        Optional<Etudiant> etudiantOptional = etudiantDao.trouver("A57");
        Etudiant etudiant  = etudiantOptional.orElseThrow(()->new EtudiantNonTrouveException("A57"));
    }
}
