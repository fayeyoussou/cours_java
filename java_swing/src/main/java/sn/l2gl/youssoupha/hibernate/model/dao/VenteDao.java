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

    private Session ouvrir() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public Vente inserer(Vente entity) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            return entity;
        }
    }

    @Override
    public Optional<Vente> trouver(String numero) {
        try (Session session = ouvrir()) {
            return Optional.ofNullable(session.find(Vente.class, numero));
        }
    }

    @Override
    public List<Vente> listerTous() {
        try (Session session = ouvrir()) {
            // On initialise les produits liés dans la session pour l'affichage.
            return session.createQuery(
                            "select distinct v from Vente v " +
                                    "left join fetch v.produits " +
                                    "order by v.numero",
                            Vente.class)
                    .list();
        }
    }

    @Override
    public Optional<Vente> modifier(Vente entity) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction();
            Vente vente = session.merge(entity);
            tx.commit();
            return Optional.of(vente);
        }
    }

    @Override
    public boolean supprimer(String numero) {
        try (Session session = ouvrir()) {
            Vente vente = session.find(Vente.class, numero);
            if (vente == null) {
                return false;
            }
            Transaction tx = session.beginTransaction();
            // On retire d'abord les liens côté propriétaire (Produit) pour nettoyer la jointure.
            List<Produit> lies = session.createQuery(
                            "select distinct p from Produit p left join fetch p.ventes v where v.numero = :num",
                            Produit.class)
                    .setParameter("num", numero)
                    .list();
            for (Produit p : lies) {
                p.getVentes().removeIf(v -> v.getNumero().equals(numero));
                session.merge(p);
            }
            session.remove(vente);
            tx.commit();
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
            Transaction tx = session.beginTransaction();
            Vente vente = session.find(Vente.class, numeroVente);
            if (vente == null) {
                tx.rollback();
                return;
            }

            // Produits actuellement rattachés à cette vente
            List<Produit> actuels = session.createQuery(
                            "select distinct p from Produit p left join fetch p.ventes v where v.numero = :num",
                            Produit.class)
                    .setParameter("num", numeroVente)
                    .list();

            // 1) Retirer le lien chez les produits qui ne sont plus sélectionnés
            for (Produit p : actuels) {
                if (produitIds == null || !produitIds.contains(p.getId())) {
                    p.getVentes().removeIf(v -> v.getNumero().equals(numeroVente));
                    session.merge(p);
                }
            }

            // 2) Ajouter le lien chez les produits nouvellement sélectionnés
            if (produitIds != null) {
                for (Long pid : produitIds) {
                    Produit p = session.find(Produit.class, pid);
                    if (p != null && p.getVentes().stream()
                            .noneMatch(v -> v.getNumero().equals(numeroVente))) {
                        p.getVentes().add(vente);
                        session.merge(p);
                    }
                }
            }

            tx.commit();
        }
    }

    /** Liste les ventes dont le montant est supérieur ou égal au minimum donné. */
    public List<Vente> listerParMontantMinimum(int montantMinimum) {
        try (Session session = ouvrir()) {
            return session.createQuery(
                            "from Vente v where v.montant >= :montantMinimum order by v.montant desc",
                            Vente.class)
                    .setParameter("montantMinimum", montantMinimum)
                    .list();
        }
    }

    /** Liste les ventes effectuées un jour donné. */
    public List<Vente> listerParDate(LocalDate date) {
        try (Session session = ouvrir()) {
            return session.createQuery(
                            "from Vente v where v.date >= :debut and v.date < :fin",
                            Vente.class)
                    .setParameter("debut", date.atStartOfDay())
                    .setParameter("fin", date.plusDays(1).atStartOfDay())
                    .list();
        }
    }
}
