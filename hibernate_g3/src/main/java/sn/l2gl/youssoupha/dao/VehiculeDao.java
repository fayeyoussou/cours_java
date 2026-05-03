package sn.l2gl.youssoupha.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sn.l2gl.youssoupha.model.Vehicule;
import sn.l2gl.youssoupha.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class VehiculeDao implements Dao<Vehicule,Long> {
    private Session ouvrir() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public Vehicule inserer(Vehicule entity) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction();
            session.getSessionFactory();
            session.persist(entity);   // Hibernate schedules INSERT
            tx.commit();          // INSERT executed here → ID generated

            return entity;             // e now contains the generated ID
        }

    }

    @Override
    public Optional<Vehicule> trouver(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Vehicule> listerTous() {
        return List.of();
    }

    @Override
    public Optional<Vehicule> modifier(Vehicule entity) {
        return Optional.empty();
    }

    @Override
    public boolean supprimer(Long aLong) {
        return false;
    }
}
