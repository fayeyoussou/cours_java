package sn.youdev.hib.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {

    T inserer(T entity);

    Optional<T> trouver(ID id);

    List<T> listerTous();

    Optional<T> modifier(T entity);

    boolean supprimer(ID id);
}
