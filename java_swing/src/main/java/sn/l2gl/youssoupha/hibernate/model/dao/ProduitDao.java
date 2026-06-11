package sn.l2gl.youssoupha.hibernate.model.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sn.l2gl.youssoupha.hibernate.model.models.Produit;
import sn.l2gl.youssoupha.hibernate.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

/** Accès aux données pour l'entité Produit (côté propriétaire de la relation ManyToMany). */
public class ProduitDao implements Dao<Produit, Long> {

    /** Ouvre une nouvelle session Hibernate à partir de la SessionFactory. */
    private Session ouvrir() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * Insère un nouveau produit en base.
     *
     * @param entity le produit à enregistrer
     * @return le produit persisté (avec son identifiant généré)
     */
    @Override
    public Produit inserer(Produit entity) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction(); // démarre la transaction
            session.persist(entity); // enregistre la nouvelle entité dans le contexte de persistance
            tx.commit(); // valide la transaction (écriture effective en base)
            return entity;
        }
    }

    /**
     * Recherche un produit par son identifiant (clé primaire).
     *
     * @param id l'identifiant du produit recherché
     * @return un Optional contenant le produit si il existe, vide sinon
     */
    @Override
    public Optional<Produit> trouver(Long id) {
        try (Session session = ouvrir()) {
            return Optional.ofNullable(session.find(Produit.class, id)); // find peut renvoyer null -> encapsulé dans un Optional
        }
    }

    /**
     * Liste tous les produits avec leur catégorie et leurs ventes chargées.
     *
     * @return la liste complète des produits, triés par identifiant
     */
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
                    .list(); // exécute la requête et renvoie la liste des produits
        }
    }

    /**
     * Met à jour un produit existant.
     *
     * @param entity le produit portant les nouvelles valeurs
     * @return un Optional contenant le produit mis à jour
     */
    @Override
    public Optional<Produit> modifier(Produit entity) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction(); // démarre la transaction
            Produit produit = session.merge(entity); // fusionne l'entité détachée et renvoie l'instance gérée
            tx.commit(); // valide la mise à jour en base
            return Optional.of(produit);
        }
    }

    /**
     * Supprime le produit identifié par son identifiant.
     *
     * @param id l'identifiant du produit à supprimer
     * @return true si la suppression a eu lieu, false si le produit n'existe pas
     */
    @Override
    public boolean supprimer(Long id) {
        try (Session session = ouvrir()) {
            Produit produit = session.find(Produit.class, id); // récupère l'entité à supprimer
            if (produit == null) {
                return false; // rien à supprimer : le produit n'existe pas
            }
            Transaction tx = session.beginTransaction(); // démarre la transaction
            session.remove(produit); // marque l'entité pour suppression
            tx.commit(); // valide la suppression en base
            return true;
        }
    }

    /**
     * Recherche un produit par son code métier (valeur unique).
     *
     * @param code le code métier du produit
     * @return un Optional contenant le produit correspondant, vide sinon
     */
    public Optional<Produit> trouverParCode(String code) {
        try (Session session = ouvrir()) {
            return session.createQuery(
                            "from Produit p where p.code = :code",
                            Produit.class)
                    .setParameter("code", code) // lie le paramètre nommé :code
                    .uniqueResultOptional(); // attend au plus un résultat -> Optional
        }
    }

    /**
     * Liste les produits appartenant à une catégorie donnée.
     *
     * @param codeCategorie le code de la catégorie
     * @return la liste des produits de cette catégorie (catégorie et ventes chargées)
     */
    public List<Produit> listerParCategorie(String codeCategorie) {
        try (Session session = ouvrir()) {
            return session.createQuery(
                            "select distinct p from Produit p " +
                                    "left join fetch p.categorie " +
                                    "left join fetch p.ventes " +
                                    "where p.categorie.code = :codeCategorie",
                            Produit.class)
                    .setParameter("codeCategorie", codeCategorie) // lie le paramètre nommé :codeCategorie
                    .list(); // exécute la requête et renvoie la liste filtrée
        }
    }
}
