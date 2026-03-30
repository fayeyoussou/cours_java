package sn.youdev.interfaces;

import java.util.Objects;

/**
 * Interface fonctionnelle pour appliquer un calcul sur deux valeurs numeriques.
 */
public interface OperationBinaire {
    double calculer(double gauche, double droite);

    /**
     * Chaine une nouvelle operation en reutilisant l'operande droite.
     * Le resultat courant devient l'operande gauche de l'operation suivante.
     */
    default OperationBinaire puis(OperationBinaire suivante) {
        Objects.requireNonNull(suivante);
        return (gauche, droite) -> suivante.calculer(this.calculer(gauche, droite), droite);
    }
}
