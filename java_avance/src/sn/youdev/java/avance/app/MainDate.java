package sn.youdev.java.avance.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class MainDate {
    public static void main(String[] args) {
        LocalDate aujourdHui = LocalDate.now();
        LocalDate debutCours = LocalDate.of(2026, 4, 6);
        LocalDate examen = LocalDate.parse("2026-06-15");
        LocalDateTime maintenant = LocalDateTime.now();
        LocalDateTime prochainCours = debutCours.atTime(8, 30);
        LocalDateTime complet = LocalDateTime.of(2026, 4, 6, 15, 30);
        DateTimeFormatter formatCourt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // now donne la date du jour.
        IO.println("Aujourd'hui : " + aujourdHui);

        // of crée une date précise.
        IO.println("Début du cours : " + debutCours);
        IO.println("Date avec heure : " + complet);

        // parse lit une date depuis un texte.
        IO.println("Examen : " + examen);

        // plusDays ajoute des jours.
        IO.println("Dans 3 jours : " + aujourdHui.plusDays(3));

        // minusWeeks retire des semaines.
        IO.println("Il y a 2 semaines : " + aujourdHui.minusWeeks(2));

        // withDayOfMonth change seulement le jour.
        IO.println("Premier jour du mois : " + aujourdHui.withDayOfMonth(1));

        // getDayOfWeek donne le jour de la semaine.
        IO.println("Jour du cours : " + debutCours.getDayOfWeek());

        // isLeapYear vérifie si l'année est bissextile.
        IO.println("Année bissextile : " + aujourdHui.isLeapYear());

        // isBefore compare deux dates.
        IO.println("Cours avant examen : " + debutCours.isBefore(examen));

        // until calcule un écart entre deux dates.
        IO.println("Jours avant examen : " + aujourdHui.until(examen, ChronoUnit.DAYS));

        // now donne la date et l'heure actuelles.
        IO.println("Maintenant : " + maintenant);

        // atTime ajoute une heure à une date.
        IO.println("Prochain cours : " + prochainCours);

        // plusHours ajoute des heures.
        IO.println("Fin du cours : " + prochainCours.plusHours(2));

        // withMinute change seulement les minutes.
        IO.println("Heure modifiée : " + prochainCours.withMinute(45));

        // toLocalDate extrait la partie date.
        IO.println("Date seule : " + prochainCours.toLocalDate());

        // format affiche une date selon un modèle.
        IO.println("Cours formaté : " + prochainCours.format(formatCourt));
    }
}
