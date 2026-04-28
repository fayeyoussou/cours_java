package sn.cours.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import sn.cours.model.Produit;
import sn.cours.persistence.JPAUtil;
import sn.cours.service.CrudService;

import java.util.List;
import java.util.Optional;

public class ProduitServiceJPA implements CrudService<Produit, Long> {

    @Override
    public Produit save(Produit produit) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(produit);
            tx.commit();
            return produit;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Erreur save Produit", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Produit> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return Optional.ofNullable(em.find(Produit.class, id));
        } finally {
            em.close();
        }
    }

    @Override
    public List<Produit> findAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            // JPQL : on navigue dans les relations objet (p.categorie au lieu d'un JOIN sur clé)
            return em.createQuery("SELECT p FROM Produit p", Produit.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Requête JPQL avec JOIN FETCH : équivalent du JOIN SQL du projet 2
    public List<Produit> findAllAvecCategorie() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery(
                    "SELECT p FROM Produit p JOIN FETCH p.categorie ORDER BY p.categorie.libelle, p.libelle",
                    Produit.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Produit update(Produit produit) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Produit merged = em.merge(produit);
            tx.commit();
            return merged;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Erreur update Produit", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Produit p = em.find(Produit.class, id);
            if (p != null) em.remove(p);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Erreur delete Produit", e);
        } finally {
            em.close();
        }
    }
}
