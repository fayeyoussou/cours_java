package sn.cours.service;

import java.util.List;
import java.util.Optional;

// Même interface que le projet 2 — seule l'implémentation change
public interface CrudService<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    T update(T entity);

    void delete(ID id);
}
