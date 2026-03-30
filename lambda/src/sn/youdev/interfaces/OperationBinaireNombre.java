package sn.youdev.interfaces;

import java.util.Objects;

/**
 * Interface fonctionnelle pour appliquer un calcul sur deux nombres.
 *
 * @param <T> type numerique des operandes
 */
@FunctionalInterface
public interface OperationBinaireNombre<T extends Number>  {
    double calculer(T gauche, T droite);

    /**
     * Chaine une operation basee sur des doubles.
     * Le resultat courant devient l'operande gauche et l'operande droite est conservee.
     */
    default OperationBinaireNombre<T> puis(OperationBinaire suivante) {
        Objects.requireNonNull(suivante);
        return (gauche, droite) -> suivante.calculer(calculer(gauche, droite), droite.doubleValue());
    }
}
