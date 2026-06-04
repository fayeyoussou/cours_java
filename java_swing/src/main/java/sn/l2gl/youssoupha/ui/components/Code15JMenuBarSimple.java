package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 15
 */

import javax.swing.*;

// JMenuBar : ajouter des menus en haut d'une fenetre.
public class Code15JMenuBarSimple {
    private Code15JMenuBarSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 15 - JMenuBar");
        fenetre.setSize(500, 280);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenu menuFichier = new JMenu("Fichier");
        menuFichier.add(new JMenuItem("Nouveau"));
        menuFichier.add(new JMenuItem("Ouvrir"));
        menuFichier.addSeparator();
        menuFichier.add(new JMenuItem("Quitter"));

        JMenuBar barreMenu = new JMenuBar();
        barreMenu.add(menuFichier);

        fenetre.setJMenuBar(barreMenu);
        fenetre.add(new JLabel("Contenu de la fenetre", SwingConstants.CENTER));
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Ajoutez les menus "Edition" et "Aide".
     * Faites fermer la fenetre lorsque l'utilisateur choisit "Quitter".
     */
}
