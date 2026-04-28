package sn.youdev;

import sn.youdev.jdbc.ConnexionTest;
import sn.youdev.jdbc.dao.EtudiantDao;
import sn.youdev.jdbc.exceptions.EtudiantException;
import sn.youdev.jdbc.model.Etudiant;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) {

        try (var conn = ConnexionTest.getConnection()) {
            EtudiantDao dao = new EtudiantDao(conn);

            // 1. CREATE
            Etudiant e = new Etudiant(
                    null,"M2024-001", "Faye", "Youssoupha",
                    LocalDate.of(1997, 9, 19)
            );
            Long id = dao.inserer(e);
            System.out.println("[CREATE] Inséré avec id = " + id);

            // 2. READ par id
            Optional<Etudiant> trouve = dao.trouver(id);
            trouve.ifPresent(et -> System.out.println("[READ]   " + et));

            // 3. UPDATE
            e.setNom("FAYE");           // mise en majuscules
            boolean modifie = dao.modifier(e);
            System.out.println("[UPDATE] Modifié : " + modifie);

            // 4. READ ALL
            List<Etudiant> tous = dao.listerTous();
            System.out.println("[LIST]   Total : " + tous.size() + " étudiant(s)");
            tous.forEach(System.out::println);

            // 5. DELETE
            boolean supprime = dao.supprimer(id);
            System.out.println("[DELETE] Supprimé : " + supprime);
        } catch (SQLException e) {
            throw new EtudiantException(e.getMessage());
        }

    }
}
