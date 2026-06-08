package sn.l2gl.youssoupha.hibernate.model.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sn.l2gl.youssoupha.hibernate.model.models.Produit;
import sn.l2gl.youssoupha.hibernate.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

/** Accès aux données pour l'entité Produit (côté propriétaire de la relation ManyToMany). */
public class ProduitDao implements Dao<Produit, Long> {

    private Session ouvrir() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public Produit inserer(Produit entity) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            return entity;
        }
    }

    @Override
    public Optional<Produit> trouver(Long id) {
        try (Session session = ouvrir()) {
            return Optional.ofNullable(session.find(Produit.class, id));
        }
    }

    @Override
    public List<Produit> listerTous() {
        try (Session session = ouvrir()) {
            // join fetch : on charge la catégorie et les ventes dans la même session
            // pour pouvoir les afficher dans l'interface (évite LazyInitializationException).
            return session.createQuery(
                            "select distinct p from Produit p " +
                                    "left join fetch p.categorie " +
                                    "left join fetch p.ventes " +
                                    "order by p.id",
                            Produit.class)
                    .list();
        }
    }

    @Override
    public Optional<Produit> modifier(Produit entity) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction();
            Produit produit = session.merge(entity);
            tx.commit();
            return Optional.of(produit);
        }
    }

    @Override
    public boolean supprimer(Long id) {
        try (Session session = ouvrir()) {
            Produit produit = session.find(Produit.class, id);
            if (produit == null) {
                return false;
            }
            Transaction tx = session.beginTransaction();
            session.remove(produit);
            tx.commit();
            return true;
        }
    }

    /** Recherche un produit par son code métier. */
    public Optional<Produit> trouverParCode(String code) {
        try (Session session = ouvrir()) {
            return session.createQuery(
                            "from Produit p where p.code = :code",
                            Produit.class)
                    .setParameter("code", code)
                    .uniqueResultOptional();
        }
    }

    /** Liste les produits d'une catégorie donnée. */
    public List<Produit> listerParCategorie(String codeCategorie) {
        try (Session session = ouvrir()) {
            return session.createQuery(
                            "select distinct p from Produit p " +
                                    "left join fetch p.categorie " +
                                    "left join fetch p.ventes " +
                                    "where p.categorie.code = :codeCategorie",
                            Produit.class)
                    .setParameter("codeCategorie", codeCategorie)
                    .list();
        }
    }
}
