package sn.youdev.hib.app;

import sn.youdev.hib.dao.EtudiantDao;
import sn.youdev.hib.model.Etudiant;
import sn.youdev.hib.util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        EtudiantDao dao = new EtudiantDao();
        try {
            // 1. CREATE
            Etudiant e = new Etudiant(null,
                    "M2024-001", "Faye", "Youssoupha",
                    LocalDate.of(1997, 4, 12)
            );
            Etudiant e2 = new Etudiant(null,
                    "M2024-002", "Diallo", "Mamadou cire",
                    LocalDate.of(2002, 4, 9)
            );
            dao.inserer(e);
            dao.inserer(e2);

            System.out.println("[CREATE] Inséré, id = " + e.getId());

            // 2. READ par id
            dao.trouver(e.getId())
                    .ifPresent(et -> System.out.println("[READ]   " + et));

            // 3. READ par matricule (HQL)
            Optional<Etudiant> parMat = dao.parMatricule("M2024-001");
            parMat.ifPresent(et -> System.out.println("[HQL]    " + et));

            // 4. UPDATE
            e.setNom("FAYE");
            Optional<Etudiant> etudiantModifie = dao.modifier(e);
            etudiantModifie.ifPresentOrElse((etudiant -> System.out.println("Etudiant modifie "+e)),()->System.out.println("Echec de modification"));

            // 5. READ ALL
            List<Etudiant> tous = dao.listerTous();
            System.out.println("[LIST]   Total : " + tous.size());
            tous.forEach(System.out::println);

            // 6. DELETE
            boolean supprime = dao.supprimer(e.getId());
            System.out.println("[DELETE] Supprimé : " + supprime);

        } finally {
            HibernateUtil.shutdown();
        }

    }
}
