package sn.youdev;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.util.Locale;

// Utilitaire : convertit un nombre entier en lettres (français)
public class NumberToLetterUtils {

    // Instance partagée, thread-safe en lecture
    private static final RuleBasedNumberFormat FMT =
            new RuleBasedNumberFormat(Locale.FRENCH, RuleBasedNumberFormat.SPELLOUT);

    // Retourne le nombre en toutes lettres
    public static String convert(long nombre) {
        return FMT.format(nombre);
    }
}
