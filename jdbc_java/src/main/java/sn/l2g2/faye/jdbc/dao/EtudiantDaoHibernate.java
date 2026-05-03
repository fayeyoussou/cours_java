package sn.l2g2.faye.jdbc.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sn.l2g2.faye.jdbc.HibernateUtil;
import sn.l2g2.faye.jdbc.model.Etudiant;
import sn.l2g2.faye.jdbc.model.EtudiantHibernate;

import java.util.List;
import java.util.Optional;

public class EtudiantDaoHibernate {
    private Session ouvrir() {
        return HibernateUtil.getSessionFactory().openSession();
    }
    public EtudiantHibernate inserer(EtudiantHibernate e) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction();

            session.persist(e);   // Hibernate schedules INSERT
            tx.commit();          // INSERT executed here → ID generated

            return e;             // e now contains the generated ID
        }
        // session.persist(e) demande à Hibernate de gérer cet objet.
        // Au commit, Hibernate génère et exécute l'INSERT.
        // L'id est rempli automatiquement dans e après l'insertion.

    }
    public Optional<EtudiantHibernate> trouver(Long id) {
        try (Session session = ouvrir()) {
            return Optional.ofNullable(session.find(EtudiantHibernate.class, id));
        }
    }
    public List<EtudiantHibernate> listerTous() { /* en 13.5 */
        try (Session session = ouvrir()) {
            return session.createQuery(
                            "FROM EtudiantHibernate ORDER BY nom, prenom",
                            EtudiantHibernate.class)
                    .list();
        }
    }
    public Optional<EtudiantHibernate> modifier(EtudiantHibernate detache) {
        Transaction tx = null;

        try (Session session = ouvrir()) {
            tx = session.beginTransaction();

            EtudiantHibernate gere = session.find(EtudiantHibernate.class, detache.getId()); // Hibernate 7 ✔
            if (gere == null) {
                tx.rollback();
                return Optional.empty();
            }

            // Apply updates
            gere.setMatricule(detache.getMatricule());
            gere.setNom(detache.getNom());
            gere.setPrenom(detache.getPrenom());
            gere.setDateNaiss(detache.getDateNaiss());

            tx.commit(); // Hibernate does dirty checking here

            return Optional.of(gere); // updated entity

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
    public boolean supprimer(Long id) { /* en 13.7 */
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction();

            Etudiant e = session.find(Etudiant.class, id);
            if (e == null) {
                tx.rollback();
                return false;
            }
            session.remove(e);
            tx.commit();
            return true;
        }

    }






}
