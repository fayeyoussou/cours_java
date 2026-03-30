package sn.youdev.interfaces;

import java.util.Objects;

/**
 * Interface fonctionnelle pour convertir un texte vers un autre format.
 */
@FunctionalInterface
public interface ConvertisseurTexte {
    String convertir(String texte);

    default ConvertisseurTexte puis(ConvertisseurTexte suivant) {
        Objects.requireNonNull(suivant);
        return texte -> suivant.convertir(convertir(texte));
    }
}
