package sn.youdev.interfaces;

import java.util.Objects;

/**
 * Interface fonctionnelle pour verifier une condition sur un nombre entier.
 */
@FunctionalInterface
public interface VerificateurNombre {
    boolean verifier(int valeur);


}
