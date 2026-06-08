package sn.l2gl.youssoupha.hibernate.model.dao;

import java.util.List;
import java.util.Optional;

/**
 * Contrat générique d'un DAO (Data Access Object).
 *
 * @param <T>  type de l'entité
 * @param <ID> type de la clé primaire
 */
public interface Dao<T, ID> {

    /** Insère une nouvelle entité et la retourne. */
    T inserer(T entity);

    /** Recherche une entité par sa clé primaire. */
    Optional<T> trouver(ID id);

    /** Liste toutes les entités. */
    List<T> listerTous();

    /** Met à jour une entité existante. */
    Optional<T> modifier(T entity);

    /** Supprime l'entité identifiée par id. Retourne true si la suppression a eu lieu. */
    boolean supprimer(ID id);
}
