package sn.l2gl.youssoupha.hibernate_simple.controller;

import sn.l2gl.youssoupha.hibernate_simple.dao.EtudiantDao;
import sn.l2gl.youssoupha.hibernate_simple.exception.MatriculeDejaExistantException;
import sn.l2gl.youssoupha.hibernate_simple.model.Classe;
import sn.l2gl.youssoupha.hibernate_simple.model.Etudiant;
import sn.l2gl.youssoupha.hibernate_simple.view.EtudiantView;

import lombok.Getter;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * CONTRÔLEUR (MVC) des étudiants.
 * C'est lui qui instancie le DAO (modèle) et la Vue : le point d'entrée ne
 * connaît que le contrôleur. Il branche les écouteurs de la vue sur le DAO,
 * contrôle l'existence du matricule dès que l'utilisateur quitte le champ,
 * et valide la saisie à l'enregistrement.
 */
public class EtudiantController {

    private final EtudiantDao dao = new EtudiantDao();

    @Getter
    private final EtudiantView vue = new EtudiantView();

    private Etudiant enCours; // null = mode création

    public EtudiantController() {
        // Contrôle immédiat à la perte de focus du champ matricule
        vue.getChampMatricule().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                verifierMatricule();
            }
        });

        vue.getBoutonNouveau().addActionListener(e -> reinitialiser());
        vue.getBoutonEnregistrer().addActionListener(e -> enregistrer());
        vue.getBoutonSupprimer().addActionListener(e -> supprimer());
        vue.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) remplirDepuisSelection();
        });

        rafraichir();
    }

    /** Recharge la table des étudiants. */
    public void rafraichir() {
        vue.afficher(dao.listerTous());
    }

    /**
     * Contrôle « au focus » : si on est en création et que le matricule saisi
     * existe déjà, on affiche le message rouge à côté du champ.
     */
    private void verifierMatricule() {
        if (enCours != null) return; // en édition : matricule figé, pas de contrôle
        String matricule = vue.getChampMatricule().getText().trim().toUpperCase();
        if (matricule.isEmpty()) {
            vue.effacerErreurMatricule(); // rien à signaler tant que le champ est vide
        } else if (dao.existe(matricule)) {
            vue.afficherErreurMatricule("Ce matricule existe déjà.");
        } else {
            vue.afficherMatriculeDisponible("Matricule disponible.");
        }
    }

    private void remplirDepuisSelection() {
        String matricule = vue.getMatriculeSelectionne();
        if (matricule == null) return;
        dao.trouver(matricule).ifPresent(e -> {
            enCours = e;
            vue.remplir(e);
        });
    }

    private void enregistrer() {
        String matricule = vue.getChampMatricule().getText().trim().toUpperCase();
        String prenom = vue.getChampPrenom().getText().trim();
        String nom = vue.getChampNom().getText().trim();
        String dateNaissanceTexte = vue.getChampDateNaissance().getText().trim();
        Classe classe = vue.getClasseSelectionnee();

        if (matricule.isEmpty() || prenom.isEmpty() || nom.isEmpty() || dateNaissanceTexte.isEmpty()) {
            JOptionPane.showMessageDialog(vue, "Matricule, prénom, nom et date de naissance sont obligatoires.",
                    "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate dateNaissance;
        try {
            dateNaissance = LocalDate.parse(dateNaissanceTexte, EtudiantView.FORMAT_DATE);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(vue, "La date de naissance doit être au format jj/MM/aaaa.",
                    "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Etudiant etudiant = (enCours == null) ? new Etudiant() : enCours;
        etudiant.setMatricule(matricule);
        etudiant.setPrenom(prenom);
        etudiant.setNom(nom);
        etudiant.setDateNaissance(dateNaissance);
        etudiant.setClasse(classe);

        try {
            if (enCours == null) {
                dao.inserer(etudiant); // peut lever MatriculeDejaExistantException
            } else {
                dao.modifier(etudiant);
            }
        } catch (MatriculeDejaExistantException ex) {
            vue.afficherErreurMatricule("Ce matricule existe déjà.");
            JOptionPane.showMessageDialog(vue, ex.getMessage(), "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        reinitialiser();
        rafraichir();
    }

    private void supprimer() {
        String matricule = vue.getMatriculeSelectionne();
        if (matricule == null) {
            JOptionPane.showMessageDialog(vue, "Sélectionnez un étudiant à supprimer.",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int confirmation = JOptionPane.showConfirmDialog(vue,
                "Supprimer cet étudiant ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation != JOptionPane.YES_OPTION) return;

        dao.supprimer(matricule);
        reinitialiser();
        rafraichir();
    }

    private void reinitialiser() {
        enCours = null;
        vue.reinitialiser();
    }
}
