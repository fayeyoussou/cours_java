package sn.cours.service;

import java.util.List;
import java.util.Optional;

// Interface générique : T = type de l'entité, ID = type de la clé primaire
public interface CrudService<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    T update(T entity);

    void delete(ID id);
}
