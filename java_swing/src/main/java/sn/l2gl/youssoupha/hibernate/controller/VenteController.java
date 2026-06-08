package sn.l2gl.youssoupha.hibernate.controller;

import sn.l2gl.youssoupha.hibernate.model.dao.ProduitDao;
import sn.l2gl.youssoupha.hibernate.model.dao.VenteDao;
import sn.l2gl.youssoupha.hibernate.model.models.Produit;
import sn.l2gl.youssoupha.hibernate.model.models.Vente;
import sn.l2gl.youssoupha.hibernate.view.VenteView;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CONTRÔLEUR (MVC) des ventes.
 * La relation ManyToMany est gérée côté propriétaire (Produit) : après avoir
 * enregistré la vente, on appelle venteDao.mettreAJourProduits().
 */
public class VenteController {

    private final VenteDao venteDao = new VenteDao();
    private final ProduitDao produitDao = new ProduitDao();
    private final VenteView vue;

    private Vente enCours; // null = création

    public VenteController(VenteView vue) {
        this.vue = vue;

        vue.getBoutonNouveau().addActionListener(e -> reinitialiser());
        vue.getBoutonEnregistrer().addActionListener(e -> enregistrer());
        vue.getBoutonSupprimer().addActionListener(e -> supprimer());
        vue.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) remplirDepuisSelection();
        });

        rafraichir();
    }

    /** Recharge la table des ventes et la liste des produits disponibles. */
    public void rafraichir() {
        vue.rechargerProduits(produitDao.listerTous());
        vue.afficher(venteDao.listerTous());
    }

    private void remplirDepuisSelection() {
        String numero = vue.getNumeroSelectionne();
        if (numero == null) return;
        venteDao.trouver(numero).ifPresent(v -> {
            enCours = v;
            vue.remplir(v);
            // Retrouver les produits liés à cette vente (via le côté propriétaire)
            List<Long> idsLies = produitDao.listerTous().stream()
                    .filter(p -> p.getVentes() != null && p.getVentes().stream()
                            .anyMatch(x -> x.getNumero().equals(numero)))
                    .map(Produit::getId)
                    .collect(Collectors.toList());
            vue.selectionnerProduits(idsLies);
        });
    }

    private void enregistrer() {
        String numero = vue.getChampNumero().getText().trim();
        String montantTexte = vue.getChampMontant().getText().trim();
        String dateTexte = vue.getChampDate().getText().trim();

        if (numero.isEmpty() || montantTexte.isEmpty()) {
            JOptionPane.showMessageDialog(vue, "Le numéro et le montant sont obligatoires.",
                    "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int montant;
        try {
            montant = Integer.parseInt(montantTexte);
            if (montant < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vue, "Le montant doit être un entier positif.",
                    "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        LocalDateTime date;
        try {
            date = dateTexte.isEmpty() ? LocalDateTime.now() : LocalDateTime.parse(dateTexte, VenteView.FORMAT);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vue,
                    "Format de date invalide. Exemple attendu : 2026-06-08 14:30",
                    "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Vente vente = (enCours == null) ? new Vente() : enCours;
        vente.setNumero(numero);
        vente.setMontant(montant);
        vente.setDate(date);

        if (enCours == null) {
            if (venteDao.trouver(numero).isPresent()) {
                JOptionPane.showMessageDialog(vue, "Ce numéro de vente existe déjà.",
                        "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }
            venteDao.inserer(vente);
        } else {
            venteDao.modifier(vente);
        }

        // Mise à jour de la relation ManyToMany côté propriétaire (Produit)
        venteDao.mettreAJourProduits(numero, vue.getProduitsSelectionnesIds());

        reinitialiser();
        rafraichir();
    }

    private void supprimer() {
        String numero = vue.getNumeroSelectionne();
        if (numero == null) {
            JOptionPane.showMessageDialog(vue, "Sélectionnez une vente à supprimer.",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int confirmation = JOptionPane.showConfirmDialog(vue,
                "Supprimer la vente « " + numero + " » ?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation != JOptionPane.YES_OPTION) return;

        venteDao.supprimer(numero);
        reinitialiser();
        rafraichir();
    }

    private void reinitialiser() {
        enCours = null;
        vue.reinitialiser();
    }
}
