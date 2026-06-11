package sn.l2gl.youssoupha.hibernate.view;

import sn.l2gl.youssoupha.hibernate.controller.CategorieController;
import sn.l2gl.youssoupha.hibernate.controller.ProduitController;
import sn.l2gl.youssoupha.hibernate.controller.VenteController;
import sn.l2gl.youssoupha.hibernate.util.HibernateUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre principale de l'application « Gestion de stock » (Hibernate + Swing, MVC).
 *
 * Pour chaque entité : on instancie la Vue (composants), puis le Contrôleur
 * (logique) — l'ordre Modèle → Vue → Contrôleur du pattern MVC.
 */
public class AppHibernate {

    // VUES (placées dans les onglets)
    private final CategorieView categorieView = new CategorieView();
    private final ProduitView produitView = new ProduitView();
    private final VenteView venteView = new VenteView();

    // CONTRÔLEURS (branchent les écouteurs sur les vues, agissent sur les DAO)
    private final CategorieController categorieController = new CategorieController(categorieView);
    private final ProduitController produitController = new ProduitController(produitView);
    private final VenteController venteController = new VenteController(venteView);

    private final JTabbedPane onglets = new JTabbedPane();

    public AppHibernate() {
        JFrame fenetre = new JFrame("Gestion de stock — Hibernate + Swing (MVC)");
        fenetre.setSize(900, 600);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // fermeture gérée manuellement

        onglets.addTab("Catégories", categorieView);
        onglets.addTab("Produits", produitView);
        onglets.addTab("Ventes", venteView);

        // Changer d'onglet rafraîchit ses données et listes de référence
        // (une catégorie ajoutée doit apparaître dans la combo des produits, etc.)
        onglets.addChangeListener(e -> rafraichirOngletActif());

        fenetre.setJMenuBar(construireMenu(fenetre));
        fenetre.add(onglets, BorderLayout.CENTER);
        fenetre.setVisible(true);

        fenetre.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                quitter(fenetre);
            }
        });
    }

    private JMenuBar construireMenu(JFrame fenetre) {
        JMenuBar barre = new JMenuBar();

        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem itemRafraichir = new JMenuItem("Rafraîchir tout");
        JMenuItem itemQuitter = new JMenuItem("Quitter");
        itemRafraichir.addActionListener(e -> rafraichirTout());
        itemQuitter.addActionListener(e -> quitter(fenetre));
        menuFichier.add(itemRafraichir);
        menuFichier.addSeparator();
        menuFichier.add(itemQuitter);

        JMenu menuAffichage = new JMenu("Affichage");
        JMenuItem itemCategories = new JMenuItem("Catégories");
        JMenuItem itemProduits = new JMenuItem("Produits");
        JMenuItem itemVentes = new JMenuItem("Ventes");
        // Chaque item réaffiche les onglets (au cas où la documentation est ouverte)
        // puis sélectionne l'onglet voulu.
        itemCategories.addActionListener(e -> { afficherOnglets(fenetre); onglets.setSelectedIndex(0); });
        itemProduits.addActionListener(e -> { afficherOnglets(fenetre); onglets.setSelectedIndex(1); });
        itemVentes.addActionListener(e -> { afficherOnglets(fenetre); onglets.setSelectedIndex(2); });
        menuAffichage.add(itemCategories);
        menuAffichage.add(itemProduits);
        menuAffichage.add(itemVentes);

        JMenu menuAide = new JMenu("Aide");
        JMenuItem itemDocumentation = new JMenuItem("Documentation");
        itemDocumentation.addActionListener(e -> afficherDocumentation(fenetre));
        JMenuItem itemApropos = new JMenuItem("À propos");
        itemApropos.addActionListener(e -> afficherApropos(fenetre));
        menuAide.add(itemDocumentation);
        menuAide.add(itemApropos);

        barre.add(menuFichier);
        barre.add(menuAffichage);
        barre.add(menuAide);
        return barre;
    }

    /**
     * Remplace le contenu central de la fenêtre (les onglets) par un élément
     * texte affichant la documentation de l'application.
     */
    private void afficherDocumentation(JFrame fenetre) {
        String texte =
                "DOCUMENTATION — Gestion de stock\n" +
                "================================\n\n" +
                "Application de démonstration Hibernate (JPA) + Java Swing, architecture MVC.\n\n" +
                "ONGLETS\n" +
                "-------\n" +
                "• Catégories : créer, modifier et supprimer les catégories de produits.\n" +
                "• Produits   : gérer les produits, chacun rattaché à une catégorie.\n" +
                "• Ventes     : enregistrer les ventes, associées à un ou plusieurs produits.\n\n" +
                "RELATIONS\n" +
                "---------\n" +
                "• Produit → 1 Catégorie (ManyToOne)\n" +
                "• Produit ↔ Ventes      (ManyToMany)\n\n" +
                "MENUS\n" +
                "-----\n" +
                "• Fichier   : rafraîchir toutes les données ou quitter l'application.\n" +
                "• Affichage : revenir aux onglets et sélectionner Catégories / Produits / Ventes.\n" +
                "• Aide      : cette documentation et la boîte « À propos ».\n\n" +
                "Astuce : utilisez le menu « Affichage » pour revenir aux onglets de saisie.";
        afficherTexte(fenetre, texte);
    }

    /**
     * Remplace le contenu central de la fenêtre par un élément texte présentant
     * les informations « À propos » de l'application.
     */
    private void afficherApropos(JFrame fenetre) {
        String texte =
                "À PROPOS\n" +
                "========\n\n" +
                "Gestion de stock\n" +
                "Démonstration Hibernate (JPA) + Java Swing, architecture MVC.\n\n" +
                "Entités : Catégorie, Produit, Vente\n" +
                "• Produit → 1 Catégorie (ManyToOne)\n" +
                "• Produit ↔ Ventes      (ManyToMany)\n\n" +
                "Astuce : utilisez le menu « Affichage » pour revenir aux onglets de saisie.";
        afficherTexte(fenetre, texte);
    }

    /** Affiche un texte (lecture seule) comme contenu central de la fenêtre. */
    private void afficherTexte(JFrame fenetre, String texte) {
        JTextArea zoneTexte = new JTextArea(texte);
        zoneTexte.setEditable(false);
        zoneTexte.setLineWrap(true);
        zoneTexte.setWrapStyleWord(true);
        zoneTexte.setMargin(new Insets(12, 12, 12, 12));
        zoneTexte.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        zoneTexte.setCaretPosition(0);

        fenetre.getContentPane().removeAll();
        fenetre.add(new JScrollPane(zoneTexte), BorderLayout.CENTER);
        fenetre.revalidate();
        fenetre.repaint();
    }

    /** Restaure les onglets comme contenu central de la fenêtre. */
    private void afficherOnglets(JFrame fenetre) {
        fenetre.getContentPane().removeAll();
        fenetre.add(onglets, BorderLayout.CENTER);
        fenetre.revalidate();
        fenetre.repaint();
    }

    private void rafraichirOngletActif() {
        switch (onglets.getSelectedIndex()) {
            case 0 -> categorieController.rafraichir();
            case 1 -> produitController.rafraichir();
            case 2 -> venteController.rafraichir();
            default -> { /* rien */ }
        }
    }

    private void rafraichirTout() {
        categorieController.rafraichir();
        produitController.rafraichir();
        venteController.rafraichir();
    }

    private void quitter(JFrame fenetre) {
        int confirmation = JOptionPane.showConfirmDialog(fenetre,
                "Quitter l'application ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation != JOptionPane.YES_OPTION) return;
        HibernateUtil.shutdown();
        fenetre.dispose();
        System.exit(0);
    }

    /** Lance l'application sur l'Event Dispatch Thread. */
    public static void lancer() {
        SwingUtilities.invokeLater(AppHibernate::new);
    }

    public static void main(String[] args) {
        lancer();
    }
}
