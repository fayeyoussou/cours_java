package sn.youdev.java.avance.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class MainDateFormat {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        LocalDateTime dateHeure = LocalDateTime.of(2026, 4, 6,7,0);
        String texteDate = "25/12/2026";
        String texteDateHeure = "25/12/2026 18:45";

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatDateHeure = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter formatFr = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", Locale.JAPANESE);
        DateTimeFormatter formatUs = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.US);
        DateTimeFormatter formatLongFr = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.FRENCH);
        DateTimeFormatter formatTexteFr = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);

        // format transforme une date en texte.
        IO.println("Date -> texte : " + date.format(formatDate));

        // format transforme une date avec heure en texte.
        IO.println("DateTime -> texte : " + dateHeure.format(formatDateHeure));

        // parse transforme un texte en date.
        IO.println("Texte -> date : " + LocalDate.parse(texteDate, formatDate));

        // parse transforme un texte en date avec heure.
        IO.println("Texte -> dateTime : " + LocalDateTime.parse(texteDateHeure, formatDateHeure));

        // ISO donne un format standard.
        IO.println("Format ISO : " + date.format(DateTimeFormatter.ISO_DATE));

        // Locale change la langue du rendu.
        IO.println("Format FR : " + date.format(formatFr));

        // Locale peut afficher le même contenu en anglais.
        IO.println("Format US : " + date.format(formatUs));

        // ofLocalizedDate applique un style prédéfini.
        IO.println("Format long FR : " + date.format(formatLongFr));

        // parse peut aussi lire un mois écrit en français.
        IO.println("Texte FR -> date : " + LocalDate.parse("6 avril 2026", formatTexteFr));

        // withLocale réutilise un format avec une autre langue.
        IO.println("DateTime FR : " + formatDateHeure.withLocale(Locale.FRENCH).format(dateHeure));
    }
}
