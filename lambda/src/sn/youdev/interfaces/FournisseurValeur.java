package sn.youdev.interfaces;

import java.util.Objects;
import java.util.function.Function;

/**
 * Interface fonctionnelle pour fournir une valeur a la demande.
 *
 * @param <T> type de la valeur fournie
 */
@FunctionalInterface
public interface FournisseurValeur<T> {
    T fournir();

    default <R> FournisseurValeur<R> transformer(Function<T, R> transformateur) {
        Objects.requireNonNull(transformateur);
        return () -> transformateur.apply(fournir());
    }
}
