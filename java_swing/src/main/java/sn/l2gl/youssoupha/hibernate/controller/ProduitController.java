package sn.l2gl.youssoupha.hibernate.controller;

import sn.l2gl.youssoupha.hibernate.model.dao.CategorieDao;
import sn.l2gl.youssoupha.hibernate.model.dao.ProduitDao;
import sn.l2gl.youssoupha.hibernate.model.dao.VenteDao;
import sn.l2gl.youssoupha.hibernate.model.models.Categorie;
import sn.l2gl.youssoupha.hibernate.model.models.Produit;
import sn.l2gl.youssoupha.hibernate.model.models.Vente;
import sn.l2gl.youssoupha.hibernate.view.ProduitView;

import javax.swing.*;
import java.util.List;

/**
 * CONTRÔLEUR (MVC) des produits.
 * Modèle : ProduitDao (+ CategorieDao et VenteDao pour alimenter combo et liste).
 * Produit étant le côté propriétaire du ManyToMany, enregistrer le produit suffit
 * à écrire la table de jointure « vente_produits ».
 */
public class ProduitController {

    private final ProduitDao produitDao = new ProduitDao();
    private final CategorieDao categorieDao = new CategorieDao();
    private final VenteDao venteDao = new VenteDao();
    private final ProduitView vue;

    private Produit enCours; // null = création

    public ProduitController(ProduitView vue) {
        this.vue = vue;

        vue.getBoutonNouveau().addActionListener(e -> reinitialiser());
        vue.getBoutonEnregistrer().addActionListener(e -> enregistrer());
        vue.getBoutonSupprimer().addActionListener(e -> supprimer());
        vue.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) remplirDepuisSelection();
        });

        rafraichir();
    }

    /** Recharge la table et les listes de référence (catégories, ventes). */
    public void rafraichir() {
        vue.rechargerReferences(categorieDao.listerTous(), venteDao.listerTous());
        vue.afficher(produitDao.listerTous());
    }

    private void remplirDepuisSelection() {
        Long id = vue.getIdSelectionne();
        if (id == null) return;
        produitDao.trouver(id).ifPresent(p -> {
            enCours = p;
            vue.remplir(p);
        });
    }

    private void enregistrer() {
        String code = vue.getChampCode().getText().trim();
        String libelle = vue.getChampLibelle().getText().trim();
        String puTexte = vue.getChampPu().getText().trim();

        if (code.isEmpty() || libelle.isEmpty() || puTexte.isEmpty()) {
            JOptionPane.showMessageDialog(vue, "Code, libellé et prix unitaire sont obligatoires.",
                    "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int pu;
        try {
            pu = Integer.parseInt(puTexte);
            if (pu < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vue, "Le prix unitaire doit être un entier positif.",
                    "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Categorie categorie = vue.getCategorieSelectionnee();
        List<Vente> ventes = vue.getVentesSelectionnees();

        Produit produit = (enCours == null) ? new Produit() : enCours;
        produit.setCode(code);
        produit.setLibelle(libelle);
        produit.setPu(pu);
        produit.setCategorie(categorie);
        produit.setVentes(ventes); // côté propriétaire : écrit la jointure

        if (enCours == null) {
            if (produitDao.trouverParCode(code).isPresent()) {
                JOptionPane.showMessageDialog(vue, "Ce code produit existe déjà.",
                        "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }
            produitDao.inserer(produit);
        } else {
            produitDao.modifier(produit);
        }

        reinitialiser();
        rafraichir();
    }

    private void supprimer() {
        Long id = vue.getIdSelectionne();
        if (id == null) {
            JOptionPane.showMessageDialog(vue, "Sélectionnez un produit à supprimer.",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int confirmation = JOptionPane.showConfirmDialog(vue,
                "Supprimer ce produit ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation != JOptionPane.YES_OPTION) return;

        produitDao.supprimer(id);
        reinitialiser();
        rafraichir();
    }

    private void reinitialiser() {
        enCours = null;
        vue.reinitialiser();
    }
}
