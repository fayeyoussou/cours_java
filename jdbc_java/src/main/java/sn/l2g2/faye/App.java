package sn.l2g2.faye;


import org.hibernate.SessionFactory;
import sn.l2g2.faye.jdbc.HibernateUtil;
import sn.l2g2.faye.jdbc.dao.EtudiantDaoHibernate;
import sn.l2g2.faye.jdbc.model.Classe;
import sn.l2g2.faye.jdbc.model.Etudiant;
import sn.l2g2.faye.jdbc.model.EtudiantHibernate;

import java.time.LocalDate;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        try (Connection conn = ConnexionTest.getConnection()) {
//            EtudiantDao dao = new EtudiantDao(conn);
//            Etudiant etudiant = new Etudiant(null, "A01", "Diallo", "Abdou", LocalDate.of(1998, 7, 18));
//            dao.inserer(etudiant);
//        }catch (SQLException e){
//
//        }
        EtudiantHibernate etudiant = new EtudiantHibernate(null, "A02", "Diallo", "Abdou", LocalDate.of(1998, 7, 18),new Classe("L3GL","Licence 2 Genie Logiciel"));
        EtudiantDaoHibernate etudiantHibernate = new EtudiantDaoHibernate();
        etudiantHibernate.inserer(etudiant);

    }
}
