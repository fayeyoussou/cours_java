package sn.youdev.hib.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sn.youdev.hib.model.Classe;
import sn.youdev.hib.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class ClasseDao implements Dao<Classe, String> {

    private Session ouvrir() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public Classe inserer(Classe classe) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction();

            session.persist(classe);
            tx.commit();

            return classe;
        }
    }

    @Override
    public Optional<Classe> trouver(String code) {
        try (Session session = ouvrir()) {
            return Optional.ofNullable(session.find(Classe.class, code));
        }
    }

    public Optional<Classe> parNom(String nom) {
        try (Session session = ouvrir()) {
            return Optional.ofNullable(
                    session.createQuery(
                                    "FROM Classe WHERE nom = :nom",
                                    Classe.class)
                            .setParameter("nom", nom)
                            .uniqueResult()
            );
        }
    }

    @Override
    public List<Classe> listerTous() {
        try (Session session = ouvrir()) {
            return session.createQuery(
                            "FROM Classe ORDER BY nom",
                            Classe.class)
                    .list();
        }
    }

    @Override
    public Optional<Classe> modifier(Classe detache) {
        Transaction tx = null;

        try (Session session = ouvrir()) {
            tx = session.beginTransaction();

            Classe gere = session.find(Classe.class, detache.getCode());
            if (gere == null) {
                tx.rollback();
                return Optional.empty();
            }

            gere.setNom(detache.getNom());

            tx.commit();

            return Optional.of(gere);

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public boolean supprimer(String code) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction();

            Classe classe = session.find(Classe.class, code);
            if (classe == null) {
                tx.rollback();
                return false;
            }

            session.remove(classe);
            tx.commit();
            return true;
        }
    }
}
