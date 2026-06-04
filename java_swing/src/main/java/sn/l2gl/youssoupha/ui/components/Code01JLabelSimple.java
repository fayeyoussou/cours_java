package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 1
 */

import javax.swing.*;
import java.awt.*;

// JLabel : afficher un texte ou une information non modifiable.
public class Code01JLabelSimple {
    private Code01JLabelSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 01 - JLabel");
        fenetre.setSize(400, 180);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello World !!! ", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        fenetre.add(label);
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Modifiez le texte, la couleur et la taille du JLabel.
     * Ajoutez ensuite un deuxieme JLabel sous le premier.
     */
}
