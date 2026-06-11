package sn.l2gl.youssoupha.hibernate.model.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sn.l2gl.youssoupha.hibernate.model.models.Categorie;
import sn.l2gl.youssoupha.hibernate.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

/** Accès aux données pour l'entité Categorie. */
public class CategorieDao implements Dao<Categorie, String> {

    /** Ouvre une nouvelle session Hibernate à partir de la SessionFactory. */
    private Session ouvrir() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * Insère une nouvelle catégorie en base.
     *
     * @param entity la catégorie à enregistrer
     * @return la catégorie persistée
     */
    @Override
    public Categorie inserer(Categorie entity) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction(); // démarre la transaction
            session.persist(entity); // enregistre la nouvelle entité dans le contexte de persistance
            tx.commit(); // valide la transaction (écriture effective en base)
            return entity;
        }
    }

    /**
     * Recherche une catégorie par son code (clé primaire).
     *
     * @param code le code de la catégorie recherchée
     * @return un Optional contenant la catégorie si elle existe, vide sinon
     */
    @Override
    public Optional<Categorie> trouver(String code) {
        try (Session session = ouvrir()) {
            return Optional.ofNullable(session.find(Categorie.class, code)); // find peut renvoyer null -> encapsulé dans un Optional
        }
    }

    /**
     * Liste toutes les catégories triées par code.
     *
     * @return la liste complète des catégories
     */
    @Override
    public List<Categorie> listerTous() {
        try (Session session = ouvrir()) {
            return session.createQuery("from Categorie order by code", Categorie.class).list(); // requête HQL récupérant toutes les catégories triées
        }
    }

    /**
     * Met à jour une catégorie existante.
     *
     * @param entity la catégorie portant les nouvelles valeurs
     * @return un Optional contenant la catégorie mise à jour
     */
    @Override
    public Optional<Categorie> modifier(Categorie entity) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction(); // démarre la transaction
            Categorie categorie = session.merge(entity); // fusionne l'entité détachée et renvoie l'instance gérée
            tx.commit(); // valide la mise à jour en base
            return Optional.of(categorie);
        }
    }

    /**
     * Supprime la catégorie identifiée par son code.
     *
     * @param code le code de la catégorie à supprimer
     * @return true si la suppression a eu lieu, false si la catégorie n'existe pas
     */
    @Override
    public boolean supprimer(String code) {
        try (Session session = ouvrir()) {
            Categorie categorie = session.find(Categorie.class, code); // récupère l'entité à supprimer
            if (categorie == null) {
                return false; // rien à supprimer : la catégorie n'existe pas
            }
            Transaction tx = session.beginTransaction(); // démarre la transaction
            session.remove(categorie); // marque l'entité pour suppression
            tx.commit(); // valide la suppression en base
            return true;
        }
    }

    /**
     * Recherche les catégories dont le libellé contient le texte fourni (recherche insensible à la casse).
     *
     * @param libelle le fragment de libellé à rechercher
     * @return la liste des catégories correspondantes, triées par code
     */
    public List<Categorie> rechercherParLibelle(String libelle) {
        try (Session session = ouvrir()) {
            return session.createQuery(
                            "from Categorie c where lower(c.libelle) like lower(:libelle) order by c.code",
                            Categorie.class
                    )
                    .setParameter("libelle", "%" + libelle + "%") // encadre le terme de % pour une recherche "contient"
                    .list();
        }
    }
}
