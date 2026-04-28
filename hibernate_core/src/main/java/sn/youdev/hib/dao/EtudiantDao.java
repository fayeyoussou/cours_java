package sn.youdev.hib.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sn.youdev.hib.model.Etudiant;
import sn.youdev.hib.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class EtudiantDao implements Dao<Etudiant, Long> {

    private Session ouvrir() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public Etudiant inserer(Etudiant e) {
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

    @Override
    public Optional<Etudiant> trouver(Long id) {
        try (Session session = ouvrir()) {
            return Optional.ofNullable(session.find(Etudiant.class, id));
        }
    }


    public Optional<Etudiant> parMatricule(String mat) { /* en 13.4 */
        try (Session session = ouvrir()) {
            return Optional.ofNullable(
                    session.createQuery(
                                    "FROM Etudiant WHERE matricule = :mat",
                                    Etudiant.class)
                            .setParameter("mat", mat)
                            .uniqueResult()
            );
        }
    }

    @Override
    public List<Etudiant> listerTous() { /* en 13.5 */
        try (Session session = ouvrir()) {
            return session.createQuery(
                            "FROM Etudiant ORDER BY nom, prenom",
                            Etudiant.class)
                    .list();
        }
    }

    @Override
    public Optional<Etudiant> modifier(Etudiant detache) {
        Transaction tx = null;

        try (Session session = ouvrir()) {
            tx = session.beginTransaction();

            Etudiant gere = session.find(Etudiant.class, detache.getId()); // Hibernate 7 ✔
            if (gere == null) {
                tx.rollback();
                return Optional.empty();
            }

            // Apply updates
            gere.setMatricule(detache.getMatricule());
            gere.setNom(detache.getNom());
            gere.setPrenom(detache.getPrenom());
            gere.setDateNaiss(detache.getDateNaiss());
            gere.setClasse(detache.getClasse());

            tx.commit(); // Hibernate does dirty checking here

            return Optional.of(gere); // updated entity

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
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
