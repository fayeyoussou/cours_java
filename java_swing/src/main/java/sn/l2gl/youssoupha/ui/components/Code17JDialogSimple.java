package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 17
 */

import javax.swing.*;

// JDialog : afficher une fenetre secondaire liee a la fenetre principale.
public class Code17JDialogSimple {
    private Code17JDialogSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 17 - JDialog");
        fenetre.setSize(500, 280);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.add(new JLabel("Fenetre principale", SwingConstants.CENTER));
        fenetre.setVisible(true);

        JDialog dialogue = new JDialog(fenetre, "Informations", false);
        dialogue.setSize(300, 160);
        dialogue.setLocationRelativeTo(fenetre);
        dialogue.add(new JLabel("Ceci est un JDialog", SwingConstants.CENTER));
        dialogue.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Ajoutez un bouton dans le dialogue pour le fermer.
     * Testez ensuite la difference entre un dialogue modal et non modal.
     */
}
