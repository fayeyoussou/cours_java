package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 6
 */

import javax.swing.*;
import java.awt.*;

// JCheckBox : proposer plusieurs choix independants.
public class Code06JCheckBoxSimple {
    private Code06JCheckBoxSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 06 - JCheckBox");
        fenetre.setSize(420, 220);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new GridLayout(3, 1, 5, 5));

        fenetre.add(new JCheckBox("Java", true));
        fenetre.add(new JCheckBox("Python"));
        fenetre.add(new JCheckBox("JavaScript"));
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Ajoutez un bouton qui affiche les langages selectionnes.
     * Interdisez la validation si aucune case n'est cochee.
     */
}
