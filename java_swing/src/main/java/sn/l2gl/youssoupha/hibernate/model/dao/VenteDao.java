package sn.l2gl.youssoupha.hibernate.model.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sn.l2gl.youssoupha.hibernate.model.models.Produit;
import sn.l2gl.youssoupha.hibernate.model.models.Vente;
import sn.l2gl.youssoupha.hibernate.util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/** Accès aux données pour l'entité Vente (côté inverse de la relation ManyToMany). */
public class VenteDao implements Dao<Vente, String> {

    /** Ouvre une nouvelle session Hibernate à partir de la SessionFactory. */
    private Session ouvrir() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * Insère une nouvelle vente en base.
     *
     * @param entity la vente à enregistrer
     * @return la vente persistée
     */
    @Override
    public Vente inserer(Vente entity) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction(); // démarre la transaction
            session.persist(entity); // enregistre la nouvelle entité dans le contexte de persistance
            tx.commit(); // valide la transaction (écriture effective en base)
            return entity;
        }
    }

    /**
     * Recherche une vente par son numéro (clé primaire).
     *
     * @param numero le numéro de la vente recherchée
     * @return un Optional contenant la vente si elle existe, vide sinon
     */
    @Override
    public Optional<Vente> trouver(String numero) {
        try (Session session = ouvrir()) {
            return Optional.ofNullable(session.find(Vente.class, numero)); // find peut renvoyer null -> encapsulé dans un Optional
        }
    }

    /**
     * Liste toutes les ventes avec leurs produits liés chargés.
     *
     * @return la liste complète des ventes, triées par numéro
     */
    @Override
    public List<Vente> listerTous() {
        try (Session session = ouvrir()) {
            // On initialise les produits liés dans la session pour l'affichage.
            return session.createQuery(
                            "select distinct v from Vente v " +
                                    "left join fetch v.produits " +
                                    "order by v.numero",
                            Vente.class)
                    .list(); // exécute la requête et renvoie la liste des ventes
        }
    }

    /**
     * Met à jour une vente existante.
     *
     * @param entity la vente portant les nouvelles valeurs
     * @return un Optional contenant la vente mise à jour
     */
    @Override
    public Optional<Vente> modifier(Vente entity) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction(); // démarre la transaction
            Vente vente = session.merge(entity); // fusionne l'entité détachée et renvoie l'instance gérée
            tx.commit(); // valide la mise à jour en base
            return Optional.of(vente);
        }
    }

    /**
     * Supprime la vente identifiée par son numéro, en nettoyant au préalable la table de jointure.
     *
     * @param numero le numéro de la vente à supprimer
     * @return true si la suppression a eu lieu, false si la vente n'existe pas
     */
    @Override
    public boolean supprimer(String numero) {
        try (Session session = ouvrir()) {
            Vente vente = session.find(Vente.class, numero); // récupère la vente à supprimer
            if (vente == null) {
                return false; // rien à supprimer : la vente n'existe pas
            }
            Transaction tx = session.beginTransaction(); // démarre la transaction
            // On retire d'abord les liens côté propriétaire (Produit) pour nettoyer la jointure.
            List<Produit> lies = session.createQuery(
                            "select distinct p from Produit p left join fetch p.ventes v where v.numero = :num",
                            Produit.class)
                    .setParameter("num", numero) // lie le numéro de la vente à délier
                    .list(); // produits actuellement rattachés à cette vente
            for (Produit p : lies) {
                p.getVentes().removeIf(v -> v.getNumero().equals(numero)); // retire la vente de la collection du produit
                session.merge(p); // répercute la suppression du lien en base
            }
            session.remove(vente); // marque la vente pour suppression
            tx.commit(); // valide la suppression (liens + vente) en base
            return true;
        }
    }

    /**
     * Met à jour, côté propriétaire (Produit), l'ensemble des produits liés à une vente.
     * La table de jointure « vente_produits » étant gérée par Produit, on agit sur lui.
     *
     * @param numeroVente numéro de la vente concernée
     * @param produitIds  identifiants des produits qui doivent être liés à cette vente
     */
    public void mettreAJourProduits(String numeroVente, List<Long> produitIds) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction(); // démarre la transaction
            Vente vente = session.find(Vente.class, numeroVente); // récupère la vente concernée
            if (vente == null) {
                tx.rollback(); // vente introuvable : on annule la transaction
                return;
            }

            // Produits actuellement rattachés à cette vente
            List<Produit> actuels = session.createQuery(
                            "select distinct p from Produit p left join fetch p.ventes v where v.numero = :num",
                            Produit.class)
                    .setParameter("num", numeroVente) // lie le numéro de la vente
                    .list(); // liste des produits déjà liés

            // 1) Retirer le lien chez les produits qui ne sont plus sélectionnés
            for (Produit p : actuels) {
                if (produitIds == null || !produitIds.contains(p.getId())) { // produit absent de la nouvelle sélection
                    p.getVentes().removeIf(v -> v.getNumero().equals(numeroVente)); // retire la vente du produit
                    session.merge(p); // répercute le retrait du lien en base
                }
            }

            // 2) Ajouter le lien chez les produits nouvellement sélectionnés
            if (produitIds != null) {
                for (Long pid : produitIds) {
                    Produit p = session.find(Produit.class, pid); // charge le produit à lier
                    if (p != null && p.getVentes().stream()
                            .noneMatch(v -> v.getNumero().equals(numeroVente))) { // lien pas encore présent
                        p.getVentes().add(vente); // ajoute la vente au produit
                        session.merge(p); // répercute l'ajout du lien en base
                    }
                }
            }

            tx.commit(); // valide l'ensemble des modifications de liens
        }
    }

    /**
     * Liste les ventes dont le montant est supérieur ou égal au minimum donné.
     *
     * @param montantMinimum le montant plancher (inclus)
     * @return la liste des ventes correspondantes, triées par montant décroissant
     */
    public List<Vente> listerParMontantMinimum(int montantMinimum) {
        try (Session session = ouvrir()) {
            return session.createQuery(
                            "from Vente v where v.montant >= :montantMinimum order by v.montant desc",
                            Vente.class)
                    .setParameter("montantMinimum", montantMinimum) // lie le seuil de montant
                    .list(); // exécute la requête et renvoie les ventes filtrées
        }
    }

    /**
     * Liste les ventes effectuées un jour donné.
     *
     * @param date le jour ciblé
     * @return la liste des ventes réalisées entre le début et la fin de cette journée
     */
    public List<Vente> listerParDate(LocalDate date) {
        try (Session session = ouvrir()) {
            return session.createQuery(
                            "from Vente v where v.date >= :debut and v.date < :fin",
                            Vente.class)
                    .setParameter("debut", date.atStartOfDay()) // borne basse : minuit du jour
                    .setParameter("fin", date.plusDays(1).atStartOfDay()) // borne haute : minuit du lendemain (exclu)
                    .list(); // exécute la requête et renvoie les ventes du jour
        }
    }
}
