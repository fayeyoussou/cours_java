package sn.l2gl.youssoupha.hibernate.controller;

import sn.l2gl.youssoupha.hibernate.model.dao.CategorieDao;
import sn.l2gl.youssoupha.hibernate.model.models.Categorie;
import sn.l2gl.youssoupha.hibernate.view.CategorieView;

import javax.swing.*;
import java.util.List;

/**
 * CONTRÔLEUR (MVC) des catégories : reçoit les événements de la vue,
 * agit sur le modèle (CategorieDao), puis ordonne le rafraîchissement de la vue.
 */
public class CategorieController {

    private final CategorieDao dao = new CategorieDao(); // le modèle (accès données)
    private final CategorieView vue;

    // Catégorie en cours d'édition (null = création)
    private Categorie enCours;

    public CategorieController(CategorieView vue) {
        this.vue = vue;

        // Branchement des écouteurs : la vue n'a aucune logique métier
        vue.getBoutonChercher().addActionListener(e -> rechercher());
        vue.getBoutonTout().addActionListener(e -> { vue.getChampRecherche().setText(""); rafraichir(); });
        vue.getBoutonNouveau().addActionListener(e -> reinitialiser());
        vue.getBoutonEnregistrer().addActionListener(e -> enregistrer());
        vue.getBoutonSupprimer().addActionListener(e -> supprimer());
        vue.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) remplirDepuisSelection();
        });

        rafraichir();
    }

    /** Recharge la table depuis la base. */
    public void rafraichir() {
        vue.afficher(dao.listerTous());
    }

    private void rechercher() {
        String texte = vue.getChampRecherche().getText().trim();
        List<Categorie> resultats = texte.isEmpty()
                ? dao.listerTous()
                : dao.rechercherParLibelle(texte);
        vue.afficher(resultats);
    }

    private void remplirDepuisSelection() {
        String code = vue.getCodeSelectionne();
        if (code == null) return;
        dao.trouver(code).ifPresent(c -> {
            enCours = c;
            vue.remplir(c);
        });
    }

    private void enregistrer() {
        String code = vue.getChampCode().getText().trim();
        String libelle = vue.getChampLibelle().getText().trim();

        if (code.isEmpty() || libelle.isEmpty()) {
            JOptionPane.showMessageDialog(vue, "Le code et le libellé sont obligatoires.",
                    "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (enCours == null) {
            if (dao.trouver(code).isPresent()) {
                JOptionPane.showMessageDialog(vue, "Ce code de catégorie existe déjà.",
                        "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }
            dao.inserer(new Categorie(code, libelle));
        } else {
            enCours.setLibelle(libelle);
            dao.modifier(enCours);
        }

        reinitialiser();
        rafraichir();
    }

    private void supprimer() {
        String code = vue.getCodeSelectionne();
        if (code == null) {
            JOptionPane.showMessageDialog(vue, "Sélectionnez une catégorie à supprimer.",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int confirmation = JOptionPane.showConfirmDialog(vue,
                "Supprimer la catégorie « " + code + " » ?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation != JOptionPane.YES_OPTION) return;

        try {
            dao.supprimer(code);
            reinitialiser();
            rafraichir();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue,
                    "Suppression impossible : des produits utilisent peut-être cette catégorie.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reinitialiser() {
        enCours = null;
        vue.reinitialiser();
    }
}
