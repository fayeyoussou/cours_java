package sn.l2gl.youssoupha.hibernate.model.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sn.l2gl.youssoupha.hibernate.model.models.Categorie;
import sn.l2gl.youssoupha.hibernate.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

/** Accès aux données pour l'entité Categorie. */
public class CategorieDao implements Dao<Categorie, String> {

    private Session ouvrir() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public Categorie inserer(Categorie entity) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            return entity;
        }
    }

    @Override
    public Optional<Categorie> trouver(String code) {
        try (Session session = ouvrir()) {
            return Optional.ofNullable(session.find(Categorie.class, code));
        }
    }

    @Override
    public List<Categorie> listerTous() {
        try (Session session = ouvrir()) {
            return session.createQuery("from Categorie order by code", Categorie.class).list();
        }
    }

    @Override
    public Optional<Categorie> modifier(Categorie entity) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction();
            Categorie categorie = session.merge(entity);
            tx.commit();
            return Optional.of(categorie);
        }
    }

    @Override
    public boolean supprimer(String code) {
        try (Session session = ouvrir()) {
            Categorie categorie = session.find(Categorie.class, code);
            if (categorie == null) {
                return false;
            }
            Transaction tx = session.beginTransaction();
            session.remove(categorie);
            tx.commit();
            return true;
        }
    }

    /** Recherche les catégories dont le libellé contient le texte fourni. */
    public List<Categorie> rechercherParLibelle(String libelle) {
        try (Session session = ouvrir()) {
            return session.createQuery(
                            "from Categorie c where lower(c.libelle) like lower(:libelle) order by c.code",
                            Categorie.class
                    )
                    .setParameter("libelle", "%" + libelle + "%")
                    .list();
        }
    }
}
