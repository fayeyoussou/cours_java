package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 2
 */

import javax.swing.*;
import java.awt.*;

// JButton : afficher un bouton cliquable. Les actions seront ajoutees dans le cours evenements.
public class Code02JButtonSimple {
    private Code02JButtonSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 02 - JButton");
        fenetre.setSize(400, 180);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));

        JButton bouton = new JButton("Valider");
        bouton.setToolTipText("Un bouton simple sans evenement");

        fenetre.add(bouton);
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Ajoutez un deuxieme bouton "Annuler".
     * Personnalisez la couleur et la taille des deux boutons.
     */
}
