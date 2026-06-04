package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 16
 */

import javax.swing.*;
import java.awt.*;

// JToolBar : regrouper des actions frequentes dans une barre d'outils.
public class Code16JToolBarSimple {
    private Code16JToolBarSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 16 - JToolBar");
        fenetre.setSize(500, 280);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());

        JToolBar barreOutils = new JToolBar();
        barreOutils.add(new JButton("Nouveau"));
        barreOutils.add(new JButton("Ouvrir"));
        barreOutils.addSeparator();
        barreOutils.add(new JButton("Enregistrer"));

        fenetre.add(barreOutils, BorderLayout.NORTH);
        fenetre.add(new JLabel("Zone de travail", SwingConstants.CENTER), BorderLayout.CENTER);
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Ajoutez un bouton "Supprimer" dans la barre d'outils.
     * Rendez la barre non flottante avec setFloatable(false).
     */
}
