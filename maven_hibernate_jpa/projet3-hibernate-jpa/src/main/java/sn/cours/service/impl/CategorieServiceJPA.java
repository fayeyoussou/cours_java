package sn.cours.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import sn.cours.model.Categorie;
import sn.cours.persistence.JPAUtil;
import sn.cours.service.CrudService;

import java.util.List;
import java.util.Optional;

public class CategorieServiceJPA implements CrudService<Categorie, Long> {

    @Override
    public Categorie save(Categorie categorie) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(categorie);
            tx.commit();
            return categorie;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Erreur save Categorie", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Categorie> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return Optional.ofNullable(em.find(Categorie.class, id));
        } finally {
            em.close();
        }
    }

    @Override
    public List<Categorie> findAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Categorie c", Categorie.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Categorie update(Categorie categorie) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Categorie merged = em.merge(categorie);
            tx.commit();
            return merged;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Erreur update Categorie", e);
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
            Categorie c = em.find(Categorie.class, id);
            if (c != null) em.remove(c);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Erreur delete Categorie", e);
        } finally {
            em.close();
        }
    }
}
