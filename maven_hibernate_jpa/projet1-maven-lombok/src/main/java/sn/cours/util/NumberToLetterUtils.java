package sn.cours.util;

public class NumberToLetterUtils {

    private static final String[] UNITES = {
        "", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf",
        "dix", "onze", "douze", "treize", "quatorze", "quinze", "seize",
        "dix-sept", "dix-huit", "dix-neuf"
    };

    private static final String[] DIZAINES = {
        "", "", "vingt", "trente", "quarante", "cinquante", "soixante",
        "soixante", "quatre-vingt", "quatre-vingt"
    };

    public static String convert(long nombre) {
        if (nombre < 0) {
            return "moins " + convert(-nombre) + " Francs CFA";
        }
        return convertirEnLettres(nombre) + " Francs CFA";
    }

    private static String convertirEnLettres(long nombre) {
        if (nombre == 0) return "zéro";

        if (nombre < 20) return UNITES[(int) nombre];

        if (nombre < 100) return convertirDizaine((int) nombre);

        if (nombre < 1_000) return convertirCentaine((int) nombre);

        if (nombre < 1_000_000) return convertirMilliers(nombre);

        if (nombre < 1_000_000_000L) return convertirMillions(nombre);

        return convertirMilliards(nombre);
    }

    private static String convertirDizaine(int n) {
        int dizaine = n / 10;
        int unite   = n % 10;

        // 70-79 : soixante + 10..19
        if (dizaine == 7) {
            return "soixante-" + UNITES[10 + unite];
        }
        // 90-99 : quatre-vingt + 10..19
        if (dizaine == 9) {
            return "quatre-vingt-" + UNITES[10 + unite];
        }
        // 80 : quatre-vingts (avec s si seul), 81-89 : quatre-vingt-x
        if (dizaine == 8) {
            if (unite == 0) return "quatre-vingts";
            return "quatre-vingt-" + UNITES[unite];
        }

        String base = DIZAINES[dizaine];
        if (unite == 0) return base;
        // liaison "et" pour 21, 31, 41, 51, 61, 71
        if (unite == 1 && dizaine < 8) return base + " et un";
        return base + "-" + UNITES[unite];
    }

    private static String convertirCentaine(int n) {
        int centaines = n / 100;
        int reste     = n % 100;

        String prefixe = centaines == 1 ? "cent" : UNITES[centaines] + " cent";

        if (reste == 0) {
            // "deux cents" prend un s si pas suivi d'un autre nombre
            return centaines == 1 ? "cent" : UNITES[centaines] + " cents";
        }
        return prefixe + " " + convertirEnLettres(reste);
    }

    private static String convertirMilliers(long n) {
        long milliers = n / 1_000;
        long reste    = n % 1_000;

        // "mille" est invariable : jamais "un mille", jamais "milles"
        String prefixe = milliers == 1 ? "mille" : convertirEnLettres(milliers) + " mille";

        if (reste == 0) return prefixe;
        return prefixe + " " + convertirEnLettres(reste);
    }

    private static String convertirMillions(long n) {
        long millions = n / 1_000_000;
        long reste    = n % 1_000_000;

        String prefixe = millions == 1
                ? "un million"
                : convertirEnLettres(millions) + " millions";

        if (reste == 0) return prefixe;
        return prefixe + " " + convertirEnLettres(reste);
    }

    private static String convertirMilliards(long n) {
        long milliards = n / 1_000_000_000L;
        long reste     = n % 1_000_000_000L;

        String prefixe = milliards == 1
                ? "un milliard"
                : convertirEnLettres(milliards) + " milliards";

        if (reste == 0) return prefixe;
        return prefixe + " " + convertirEnLettres(reste);
    }
}
